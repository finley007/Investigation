package stock.rule.impl;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import stock.feed.http.MyStockInfoFeeder;
import stock.model.MyStock;
import stock.model.Stock;
import stock.util.DateUtils;

public class FallTrendRuleTest {

	private Stock stock = new Stock();

	@Before
	public void before() throws Exception {
		stock.setCode("sh603828");
		MyStockInfoFeeder feeder = new MyStockInfoFeeder();
		feeder.feedInfo(stock);
	}

	@After
	public void after() throws Exception {
	}


	
	@Test
	public void testIsSatisfy() {
		try { 
			FallTrendRule rule = new FallTrendRule();
			Assert.assertTrue(rule.isSatisfy(stock)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
