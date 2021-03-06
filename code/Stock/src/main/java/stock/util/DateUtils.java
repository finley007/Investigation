package stock.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import stock.context.StockAppContext;
import stock.http.HTTPCaller;
import stock.manager.StockManager;

public class DateUtils {
	
	private static Integer SUNDAY = 7;
	private static Integer SATURDAY = 6;
	
	private static final Integer CLOSE_HOUR = 15;
	private static final Integer CLOSE_MIN = 30;

	private static final String NORMAL_DAY = "0";
	private static final String WEEKEND_DAY = "1";
	private static final String VOCATION_DAY = "2";

	private static final String VOCATION_CHECK_URL = "http://www.easybots.cn/api/holiday.php?d={0}";
	
	private static StockManager stockManager = (StockManager)StockAppContext.getBean("stockManager");
	
	private static Map<String, Integer> dateStatus;
	
	private static Map<String, Integer> getDateStatus() throws Exception {
		if (dateStatus == null) {
			dateStatus = stockManager.initDateStatus();
		}
		return dateStatus;
	}
	
	public static Date getDayBeforeNDays(Date date, int n) {
		Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - n);  
        return c.getTime();
	}
	
	public static Date getDayAfterNDays(Date date, int n) {
		Calendar c = Calendar.getInstance();  
		c.setTime(date);  
		int day = c.get(Calendar.DATE);  
		c.set(Calendar.DATE, day + n);  
		return c.getTime();
	}

	public static Integer getWeekDay(Date date) {
		 Calendar c = Calendar.getInstance();
		 c.setTime(date);
		 int dayForWeek = 0;
		 if (c.get(Calendar.DAY_OF_WEEK) == 1){
		   dayForWeek = SUNDAY;
		 } else {
		   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		 }
		 return dayForWeek;
	}
	
	public static Boolean isWeekEnd(Date date) {
		Integer day = getWeekDay(date);
		return day == SUNDAY || day == SATURDAY;
	}

	/**
	 * 判断是否收盘
	 *
	 * @param date the date 当前时间
	 * @return the boolean
	 */
	public static Boolean isOverCloseTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
//		hour += 6; //solve time difference
		if (hour > CLOSE_HOUR || (hour == CLOSE_HOUR && min > CLOSE_MIN)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<String> getRecentDate(int days) throws Exception {
		List<String> result = new ArrayList<String>();
		int beforeDays = 0;
		if (isOverCloseTime(new Date())) {
			beforeDays --;
		}
		for (int i = 1; i < days + 1; i++) { //StockConstants.HISTORY_DAILY_INFO_SIZE + 1: Count more 1 day for judging limit up
			Date date = null;
			do {
				date = getDayBeforeNDays(new Date(), ++beforeDays);
			} while (isInvalidDay(date));
			result.add(StockConstants.sdf_date.format(date));
		}
		return result;
	}
	
	public static Boolean isInvalidDay(Date date) throws Exception {
		if (isWeekEnd(date) && isVocationDay(date)) {
			return true;
		} 
		return getDateStatus().get(StockConstants.sdf_date.format(date)) == StockConstants.INVALID_DAY;
	}
	
	public static Boolean isVocationDay(Date date) throws Exception {
		HTTPCaller caller = HTTPCaller.getIns(HTTPCaller.Method.Get, MessageFormat.format(VOCATION_CHECK_URL, new Object[]{StockConstants.sdf_date.format(date)}));
		Map res = JsonHelper.fromJson(Map.class, caller.callHTTPServ(null));
		if (res != null && res.size() > 0 &&
				(res.containsKey(WEEKEND_DAY) || res.containsKey(VOCATION_DAY))) {
			return true;
		}
		return false;
	}
	
	public static Date clearTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
}
