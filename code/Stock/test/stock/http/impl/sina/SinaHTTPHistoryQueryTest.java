package stock.http.impl.sina;

import org.junit.Test;

import stock.vo.StockInfo;

public class SinaHTTPHistoryQueryTest {
	
	private SinaHTTPHistoryQuery query = new SinaHTTPHistoryQuery();

	@Test
	public void testRichStockInfo() {
		StockInfo stock = new StockInfo();
		stock.setCode("sh601006");
		try {
			query.richStockInfo(stock);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
