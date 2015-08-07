package stock.timer.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import stock.timer.TimerConstants;

public class MyStockMonitorJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = context.getMergedJobDataMap();
		if (dataMap.get(TimerConstants.JOB_KEY_STOCK_CODE) != null) {
			String stockCode = dataMap.get(TimerConstants.JOB_KEY_STOCK_CODE).toString();
			System.out.println("Check stock code:" + stockCode);
		}
	}

}
