package stock.rule.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import stock.http.impl.MyHTTPQuery;
import stock.util.DateUtils;
import stock.vo.StockInfo;

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
			StockInfo stock = new StockInfo();
			stock.setCode("sh603939");
			MyHTTPQuery httpQuery = new MyHTTPQuery();
			httpQuery.richStockInfo(stock);
			TrendRule rule = new TrendRule();
			Assert.assertFalse(rule.isSatisfy(stock)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
