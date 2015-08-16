package stock.util;

import junit.framework.Assert;

import org.junit.Test;


public class StockUtilsTest {

	@Test
	public void testCaculateFee() {
		Assert.assertEquals(5.3, StockUtils.caculateFee(StockConstants.ACTION_TYPE_BUY, 500, 6.0));
		Assert.assertEquals(8.7, StockUtils.caculateFee(StockConstants.ACTION_TYPE_SELL, 500, 6.8));
	}

}
