package stock.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	
	private static Integer SUNDAY = 7;
	private static Integer SATURDAY = 6;
	
	private static final Integer CLOSE_HOUR = 15;
	private static final Integer CLOSE_MIN = 30;
	
	public static Date getDayBeforeNDays(Date date, int n) {
		Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - n);  
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
	
	public static Boolean isOverCloseTime(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		hour += 6; //solve time difference
		if (hour > CLOSE_HOUR || (hour == CLOSE_HOUR && min > CLOSE_MIN)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<String> getRecentDate() {
		List<String> result = new ArrayList<String>();
		int beforeDays = 0;
		if (DateUtils.isOverCloseTime(new Date())) {
			beforeDays --;
		}
		for (int i = 0; i < StockConstants.HISTORY_DAILY_INFO_SIZE + 1; i++) { //StockConstants.HISTORY_DAILY_INFO_SIZE + 1: Count more 1 day for judging limit up
			Date date = null;
			do {
				date = DateUtils.getDayBeforeNDays(new Date(), ++beforeDays);
			} while (DateUtils.isWeekEnd(date));
			result.add(StockConstants.sdf_date.format(date));
		}
		return result;
	}
}
