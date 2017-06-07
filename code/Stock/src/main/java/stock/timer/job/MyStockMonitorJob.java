package stock.timer.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import stock.manager.StockManager;
import stock.manager.impl.StockManagerImpl;
import stock.timer.TimerConstants;
import stock.util.StockConstants;

public class MyStockMonitorJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap dataMap = context.getMergedJobDataMap();
		if (dataMap.get(TimerConstants.JOB_KEY_STOCK_CODE) != null) {
			String stockCode = dataMap.get(TimerConstants.JOB_KEY_STOCK_CODE).toString();
			StockManager query = new StockManagerImpl();
			try {
				query.addAlert(stockCode, StockConstants.ALERT_TYPE_STOCK_DROP, "The stock has drop!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
