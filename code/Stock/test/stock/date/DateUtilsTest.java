package stock.date;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import stock.util.StockConstants;

public class DateUtilsTest {

	@Test
	public void testGetDayBeforeNDays() {
		try {
			Date date = StockConstants.sdf_date.parse("2015-08-09");
			Date newDate = DateUtils.getDayBeforeNDays(date, 3);
			Assert.assertEquals("2015-08-06", StockConstants.sdf_date.format(newDate));
			newDate = DateUtils.getDayBeforeNDays(date, 10);
			Assert.assertEquals("2015-07-30", StockConstants.sdf_date.format(newDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetWeekDay() {
		try {
			Date date = StockConstants.sdf_date.parse("2015-08-09");
			Assert.assertEquals(7, DateUtils.getWeekDay(date).intValue());
			date = StockConstants.sdf_date.parse("2015-08-07");
			Assert.assertEquals(5, DateUtils.getWeekDay(date).intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIsWeekEnd() {
		try {
			Date date = StockConstants.sdf_date.parse("2015-08-09");
			Assert.assertTrue(DateUtils.isWeekEnd(date));
			date = StockConstants.sdf_date.parse("2015-08-08");
			Assert.assertTrue(DateUtils.isWeekEnd(date));
			date = StockConstants.sdf_date.parse("2015-08-04");
			Assert.assertFalse(DateUtils.isWeekEnd(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
