package stock.rule.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import stock.http.impl.MyHTTPQuery;
import stock.util.DateUtils;
import stock.vo.StockInfo;

public class FallTrendRuleTest {

	@Test
	public void testGetRecentDate() {
		TrendRule rule = new TrendRule();
		List<String> list = DateUtils.getRecentDate();
		System.out.println(list.toString());
	}
	
	@Test
	public void testIsSatisfy() {
		try { 
			StockInfo stock = new StockInfo();
			stock.setCode("sh600137");
			MyHTTPQuery httpQuery = new MyHTTPQuery();
			httpQuery.richStockInfo(stock);
			FallTrendRule rule = new FallTrendRule();
			Assert.assertFalse(rule.isSatisfy(stock)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
