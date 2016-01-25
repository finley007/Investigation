package stock.tool;

import java.util.Date;

import stock.manager.StockManager;
import stock.manager.impl.StockManagerImpl;
import stock.util.DateUtils;
import stock.util.StockConstants;

public class CalendarCreator {
	
	private static StockManager query;
	
	private static StockManager getDBQuery() {
		if (query == null) {
			query = new StockManagerImpl();
		}
		return query;
	}
	
	public static void createCalendar(Date start, Date end) throws Exception {
		getDBQuery().clearCalendar(start, end);
		Date sd = DateUtils.clearTime(start);
		Date ed = DateUtils.clearTime(end);
		while (sd.compareTo(ed) <= 0) {
			if (DateUtils.isWeekEnd(sd)) {
				getDBQuery().insertCalendar(sd, StockConstants.INVALID_DAY);
			} else {
				getDBQuery().insertCalendar(sd, StockConstants.VALID_DAY);
			}
			sd = DateUtils.getDayAfterNDays(sd, 1);
		}
	}
	
	public static void createCalendar(String start, String end) throws Exception {
		Date dStart = StockConstants.sdf_date.parse(start);
		Date dEnd = StockConstants.sdf_date.parse(end);
		createCalendar(dStart, dEnd);
	}

}
