package stock.timer;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import stock.timer.job.HelloJob;

public class Timer {
	
	private static Scheduler sched;
	
	private static Scheduler getSched() throws Exception {
		if (sched == null) {
			SchedulerFactory sf = new StdSchedulerFactory();
			sched = sf.getScheduler();
		}
		return sched;
	}
	
	public static void start() throws Exception {
		getSched().start();
	}
	
	public static void shutdown() throws Exception {
		getSched().shutdown();
	}
	
	public static JobDetail createJob(Class clz, String jobName, String groupName) {
		return newJob(HelloJob.class).withIdentity(jobName, groupName).build();
	}
	
	public static Trigger createTrigger(String triggerName, String groupName, Date startTime, int interval) {
		return newTrigger().withIdentity(triggerName, groupName).startAt(startTime).withSchedule(simpleSchedule()
				.withIntervalInSeconds(interval).repeatForever()).build();
	}
	
	public static void scheduleJob(JobDetail job, Trigger trigger) throws Exception {
		getSched().scheduleJob(job, trigger);
	}

}
