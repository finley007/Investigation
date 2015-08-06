package stock.timer;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import stock.timer.job.HelloJob;

public class QuartzTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            // Grab the Scheduler instance from the Factory 
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
            // and start it off
         // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();
         // compute a time that is on the next round minute
            Date runTime = evenMinuteDate(new Date());

            // Trigger the job to run on the next round minute
            Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(runTime)
                .build();
            sched.scheduleJob(job, trigger);
            sched.start();
            Thread.sleep(90L * 1000L);
            sched.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}
