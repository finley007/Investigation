package stock.vo;

import junit.framework.Assert;

import org.junit.Test;

public class DailyPriceVOTest {

	@Test
	public void testGetDaysToToday() {
		try {
			DailyPriceVO vo = new DailyPriceVO("2015-08-07");
			Assert.assertEquals(Integer.valueOf(1), vo.getDaysToToday());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
