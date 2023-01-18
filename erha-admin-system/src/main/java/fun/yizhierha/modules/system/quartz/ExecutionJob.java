package fun.yizhierha.modules.system.quartz;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import fun.yizhierha.common.utils.SpringContextHolder;
import fun.yizhierha.common.utils.StringUtils;
import fun.yizhierha.config.thread.ThreadPoolExecutorUtil;
import fun.yizhierha.modules.system.domain.SysQuartzJob;
import fun.yizhierha.modules.system.domain.SysQuartzLog;
import fun.yizhierha.modules.system.service.SysQuartzJobService;
import fun.yizhierha.modules.system.service.SysQuartzLogService;
import fun.yizhierha.modules.system.service.impl.SysQuartzJobServiceImpl;
import fun.yizhierha.modules.system.service.impl.SysQuartzLogServiceImpl;
import fun.yizhierha.common.utils.ValidUtils;
import fun.yizhierha.tools.other.domain.vo.SendEmailVo;
import fun.yizhierha.tools.other.service.ToolEmailService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecutionJob extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static ExecutorService executor = ThreadPoolExecutorUtil.getPoll("eh-quartz-job");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 拿到quartzJob信息，原因：cronTrigger.getJobDataMap().put(SysQuartzJob.JOB_KEY, quartzJob);
        SysQuartzJob sysQuartzJob = (SysQuartzJob) context.getMergedJobDataMap().get(SysQuartzJob.JOB_KEY);
        // 获取Spring相关的bean
        // quartz日志
        SysQuartzLogService sysQuartzLogService = SpringContextHolder.getBean(SysQuartzLogServiceImpl.class);
        // quartz服务：开启子任务
        SysQuartzJobService sysQuartzJobService = SpringContextHolder.getBean(SysQuartzJobServiceImpl.class);

        SysQuartzLog sysQuartzLog = new SysQuartzLog();
        sysQuartzLog.setBeanName(sysQuartzJob.getBeanName());
        sysQuartzLog.setCreateTime(sysQuartzJob.getCreateTime());
        sysQuartzLog.setCronExpression(sysQuartzJob.getCronExpression());
        sysQuartzLog.setJobName(sysQuartzJob.getJobName());
        sysQuartzLog.setMethodName(sysQuartzJob.getMethodName());
        sysQuartzLog.setParams(sysQuartzJob.getParams());
        sysQuartzLog.setExecTime(new Date());

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            QuartzRunnable quartzRunnable = new QuartzRunnable(sysQuartzJob.getBeanName(), sysQuartzJob.getMethodName(), sysQuartzJob.getParams());
            Future<?> future = executor.submit(quartzRunnable);
            future.get();
            long times = System.currentTimeMillis() - startTime;

            sysQuartzLog.setTime(times);
            sysQuartzLog.setIsSuccess(true);

            logger.info("任务执行成功，任务名称：" + sysQuartzJob.getJobName() + ", 执行时间：" + times + "毫秒");

            // 判断是否有子任务
            if (StringUtils.isNotBlank(sysQuartzJob.getSubTask())){
                String[] taskIds = sysQuartzJob.getSubTask().split("[,，]");
                // 执行子任务
                sysQuartzJobService.executionSubJob(taskIds);
            }


        }catch (Exception ex){
            sysQuartzLog.setIsSuccess(false);
            sysQuartzLog.setExceptionDetail(ValidUtils.getStackTrace(ex));
            long times = System.currentTimeMillis() - startTime;
            sysQuartzLog.setTime(times);
            logger.error("任务执行失败，任务名称：" + sysQuartzJob.getJobName() + ", 执行时间：" + times + "毫秒");

            // 如果任务失败就暂停
            if (sysQuartzJob.getPauseAfterFailure() != null && Boolean.TRUE.equals(sysQuartzJob.getPauseAfterFailure())){
                sysQuartzJob.setIsPause(false);
                sysQuartzJobService.toggleIsPause(sysQuartzJob);
            }

            // 邮箱报警
            if (!StringUtils.isBlank(sysQuartzJob.getEmail())){
                ToolEmailService emailService = SpringContextHolder.getBean(ToolEmailService.class);
                SendEmailVo emailVo = taskAlarm(sysQuartzJob, ValidUtils.getStackTrace(ex));
                emailService.sendEmail(emailVo);
            }

        }finally {
            sysQuartzLogService.save(sysQuartzLog);
        }


    }

    private SendEmailVo taskAlarm(SysQuartzJob quartzJob, String msg) {
        SendEmailVo emailVo = new SendEmailVo();
        emailVo.setTitle("定时任务【"+ quartzJob.getJobName() +"】执行失败，请尽快处理！");
        Map<String, Object> data = new HashMap<>(16);
        data.put("task", quartzJob);
        data.put("msg", msg);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("/email/taskAlarm.ftl");
        emailVo.setContent(template.render(data));
        emailVo.setAddress(quartzJob.getEmail());
        return emailVo;
    }
}
