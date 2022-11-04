package fun.yizhierha.modules.system.quartz;

import fun.yizhierha.common.exception.BadRequestException;
import fun.yizhierha.modules.system.domain.SysQuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;
import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
@Slf4j
public class QuartzManage {

    private static final String JOB_NAME = "TASK_";

    @Resource
    Scheduler scheduler;

    /**
     * 添加一个job
     * @param quartzJob
     */
    public void addJob(SysQuartzJob quartzJob) throws Exception{

        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                withIdentity(JOB_NAME + quartzJob.getJobId()).build();

        //通过触发器名和cron 表达式创建 Trigger
        Trigger cronTrigger = newTrigger()
                .withIdentity(JOB_NAME + quartzJob.getJobId())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                .build();

        cronTrigger.getJobDataMap().put(SysQuartzJob.JOB_KEY, quartzJob);

        //重置启动时间
        ((CronTriggerImpl)cronTrigger).setStartTime(new Date());

        //执行定时任务
        scheduler.scheduleJob(jobDetail,cronTrigger);

        // 暂停任务
        if (quartzJob.getIsPause()) {
            pauseJob(quartzJob.getJobId());
        }

    }

    /**
     * 更新job
     * @param quartzJob /
     */
    public void updateJob(SysQuartzJob quartzJob) throws Exception{
        TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getJobId());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 如果不存在则创建一个定时任务
        if(trigger == null){
            addJob(quartzJob);
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        }
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        //重置启动时间
        ((CronTriggerImpl)trigger).setStartTime(new Date());
        trigger.getJobDataMap().put(SysQuartzJob.JOB_KEY,quartzJob);

        scheduler.rescheduleJob(triggerKey, trigger);
        // 暂停任务
        if (quartzJob.getIsPause()) {
            pauseJob(quartzJob.getJobId());
        }

    }

    /**
     * 删除一个job
     * @param quartzJobId /
     */
    public void deleteJob(Long quartzJobId) throws Exception{
        JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJobId);
        scheduler.pauseJob(jobKey);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 恢复一个job
     * @param quartzJob /
     */
    public void resumeJob(SysQuartzJob quartzJob) throws Exception{
        TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getJobId());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 如果不存在则创建一个定时任务
        if(trigger == null) {
            addJob(quartzJob);
        }
        JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 立即执行job:不管cron表达式如何，先执行一次
     * @param quartzJob /
     */
    public void runJobNow(SysQuartzJob quartzJob) throws Exception{
        TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getJobId());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 如果不存在则创建一个定时任务
        if(trigger == null) {
            addJob(quartzJob);
        }
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(SysQuartzJob.JOB_KEY, quartzJob);
        JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getJobId());
        scheduler.triggerJob(jobKey,dataMap);
    }

    /**
     * 暂停一个job
     * @param quartzJobId /
     */
    public void pauseJob(Long quartzJobId) throws Exception{
        JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJobId);
        scheduler.pauseJob(jobKey);

    }
}
