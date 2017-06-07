package stock.util;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import stock.util.DateUtils;
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
	public void testGetDayAfterNDays() {
		try {
			Date date = StockConstants.sdf_date.parse("2015-08-06");
			Date newDate = DateUtils.getDayAfterNDays(date, 3);
			Assert.assertEquals("2015-08-09", StockConstants.sdf_date.format(newDate));
			newDate = DateUtils.getDayAfterNDays(date, 31);
			Assert.assertEquals("2015-09-06", StockConstants.sdf_date.format(newDate));
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
	
	@Test
	public void testIsOverCloseTime() {
		try {
			Date date = new Date();
			Assert.assertTrue(DateUtils.isOverCloseTime(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClearTime() {
		try {
			Date date = StockConstants.sdf_time.parse("2015-09-08 21:18:20");
			date = DateUtils.clearTime(date);
			Assert.assertEquals("2015-09-08 00:00:00", StockConstants.sdf_time.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIsVocation() {
		try {
			Date date = StockConstants.sdf_date.parse("2017-06-01");
			DateUtils.isVocationDay(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
