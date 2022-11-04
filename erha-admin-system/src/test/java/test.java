
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


public class test {



    public static void main(String[] args) throws Exception{

        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = newJob(HelloJob.class)
                .withIdentity("hello")
                .usingJobData("age",16)
                .usingJobData("name","汪汪汪")
                .build();

        scheduler.start();

        CronTrigger trigger = newTrigger()
                .withIdentity("trigger", "g1")
                .startNow()
                .usingJobData("age",99)
                .withSchedule(
                       CronScheduleBuilder.cronSchedule("0/10 29 22 * * ?")
                )
                .build();

        scheduler.scheduleJob(jobDetail,trigger);

        JobKey jobKey = JobKey.jobKey("hello");

        scheduler.triggerJob(jobKey);
    }



    public static class HelloJob implements Job{
        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDetail jobDetail = context.getJobDetail();
            JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
            logger.info(String.valueOf(jobDetail.getJobDataMap().get("name")));


        }

        public void setAge(int age){
            System.out.println("set:"+age);
        }

    }

}


