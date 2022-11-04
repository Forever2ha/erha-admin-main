package fun.yizhierha.modules.system.quartz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.yizhierha.modules.system.domain.SysQuartzJob;
import fun.yizhierha.modules.system.service.impl.SysQuartzJobServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobRunner implements ApplicationRunner {
    private final QuartzManage quartzManage;
    private final SysQuartzJobServiceImpl sysQuartzJobService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<SysQuartzJob> sysQuartzJobList = sysQuartzJobService.list(new QueryWrapper<SysQuartzJob>().eq(SysQuartzJob.COL_IS_PAUSE, false));
        log.info("恢复定时任务 : 共 "+sysQuartzJobList.size()+" 个");
        int count = sysQuartzJobList.size();
        for (SysQuartzJob sysQuartzJob : sysQuartzJobList) {
            try {
                quartzManage.addJob(sysQuartzJob);
                log.info("恢复成功: "+ "["+sysQuartzJob.getJobId()+"] "+ sysQuartzJob.getJobName());
            }catch (Exception e){
                count--;
                log.info("恢复失败: "+ "["+sysQuartzJob.getJobId()+"] "+ sysQuartzJob.getJobName() + " Exception:"+e.getMessage());
            }
        }
        log.info("恢复定时任务完毕 : 成功 "+count+" 个");
    }
}
