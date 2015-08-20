package stock.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import stock.timer.Timer;
import stock.timer.TimerConstants;
import stock.timer.job.MyStockMonitorJob;

public class TimerServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String transId = request.getParameter("transId");
		String stockCode = request.getParameter("stockCode");
		Boolean dbUpdated = false;
		try {
			if (action != null && !"".equals(action)) {
				JobDetail job = Timer.createJob(MyStockMonitorJob.class, stockCode, TimerConstants.JOB_GROUP_MONITOR);
				job.getJobDataMap().put(TimerConstants.JOB_KEY_STOCK_CODE, stockCode);
				Trigger trigger = Timer.createTrigger(stockCode, TimerConstants.JOB_GROUP_MONITOR, new Date(), TimerConstants.RUM_INTERVAL_1_MIN);
				if (TimerConstants.TIMER_ACTION_START.equalsIgnoreCase(action)) {
					getDBQuery().updateMonitorStatus(transId, TimerConstants.IS_MONITOR);
					dbUpdated = true;
					Timer.scheduleJob(job, trigger);
				} else {
					getDBQuery().updateMonitorStatus(transId, TimerConstants.NOT_MONITOR);
					dbUpdated = true;
					Timer.unscheduleJob(trigger);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (dbUpdated) {
					if (TimerConstants.TIMER_ACTION_START.equalsIgnoreCase(action)) {
						getDBQuery().updateMonitorStatus(transId, TimerConstants.NOT_MONITOR);
					} else {
						getDBQuery().updateMonitorStatus(transId, TimerConstants.IS_MONITOR);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
