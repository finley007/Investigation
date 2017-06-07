package stock.http.impl.sina;

import org.junit.Test;

import stock.feed.http.sina.SinaHTTPHistoryFeeder;
import stock.model.Stock;

public class SinaHTTPHistoryFeederTest {
	
	private SinaHTTPHistoryFeeder feeder = new SinaHTTPHistoryFeeder();

	@Test
	public void testRichStockInfo() {
		Stock stock = new Stock();
		stock.setCode("sh601006");
		try {
			feeder.feedInfo(stock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
