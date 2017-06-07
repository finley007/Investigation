package stock.tool;

import java.util.Date;

import stock.context.StockAppContext;
import stock.dao.IStockOperation;
import stock.model.Calendar;
import stock.model.DateInterval;
import stock.util.DateUtils;
import stock.util.StockConstants;

public class CalendarCreator {
	
	private static IStockOperation operation = (IStockOperation)StockAppContext.getBean("stockMapper");
	
	public static void createCalendar(Date start, Date end) throws Exception {
		operation.deleteCalendarBetweenInterval(new DateInterval(start, end));
		Date sd = DateUtils.clearTime(start);
		Date ed = DateUtils.clearTime(end);
		while (sd.compareTo(ed) <= 0) {
			Calendar cal = new Calendar();
			cal.setDate(sd);
			if (DateUtils.isWeekEnd(sd)) {
				cal.setIsValid(StockConstants.INVALID_DAY);
			} else {
				cal.setIsValid(StockConstants.VALID_DAY);
			}
			operation.insertCalendar(cal);
			sd = DateUtils.getDayAfterNDays(sd, 1);
		}
	}
	
	public static void createCalendar(String start, String end) throws Exception {
		Date dStart = StockConstants.sdf_date.parse(start);
		Date dEnd = StockConstants.sdf_date.parse(end);
		createCalendar(dStart, dEnd);
	}

}
