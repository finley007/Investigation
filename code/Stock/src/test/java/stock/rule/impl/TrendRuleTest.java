package stock.rule.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stock.feed.http.MyStockInfoFeeder;
import stock.model.Stock;

public class TrendRuleTest {

	private Stock stock = new Stock();

	@Before
	public void before() throws Exception {
		stock.setCode("sh603939");
		MyStockInfoFeeder feeder = new MyStockInfoFeeder();
		feeder.feedInfo(stock);
	}

	@After
	public void after() throws Exception {
	}

	@Test
	public void testIsSatisfy() {
		try { 
			TrendRule rule = new TrendRule();
			Assert.assertTrue(rule.isSatisfy(stock)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
