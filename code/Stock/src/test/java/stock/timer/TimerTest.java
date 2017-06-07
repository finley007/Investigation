package stock.timer;

import java.util.Date;

import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import stock.timer.job.MyStockMonitorJob;

public class TimerTest {

	@Test
	public void testScheduleJob() {
		try {
			Timer.start();
			JobDetail job = Timer.createJob(MyStockMonitorJob.class, "sh600081", TimerConstants.JOB_GROUP_MONITOR);
			job.getJobDataMap().put(TimerConstants.JOB_KEY_STOCK_CODE, "sh600081");
			Trigger trigger = Timer.createTrigger("sh600081", TimerConstants.JOB_GROUP_MONITOR, new Date(), 5);
			Timer.unscheduleJob(trigger);
//			Timer.scheduleJob(job, trigger);
			while(true) {
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
