package stock.rule.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import stock.feed.http.MyStockInfoFeeder;
import stock.model.MyStock;
import stock.model.Stock;
import stock.util.DateUtils;

public class TrendRuleTest {

	@Test
	public void testGetRecentDate() {
		TrendRule rule = new TrendRule();
		List<String> list = null;
		try {
			list = DateUtils.getRecentDate(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.toString());
	}
	
	@Test
	public void testIsSatisfy() {
		try { 
			Stock stock = new Stock();
			stock.setCode("sh603939");
			MyStock myStock = new MyStock();
			myStock.setStock(stock);
			MyStockInfoFeeder feeder = new MyStockInfoFeeder();
			feeder.feedInfo(myStock);
			TrendRule rule = new TrendRule();
			Assert.assertFalse(rule.isSatisfy(stock)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
