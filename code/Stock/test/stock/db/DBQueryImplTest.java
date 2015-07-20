package stock.db;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import stock.db.connect.MysqlConnector;
import stock.vo.Stock;

public class DBQueryImplTest {
	
	private DBQuery query;

	@Before
	public void setUp() throws Exception {
		query = new DBQueryImpl();
		query.setConn(new MysqlConnector());
	}

	@Test
	public void testGetStockList() {
		try {
			List<Stock> stocks = query.getStockList();
			Assert.assertEquals(stocks.size(),1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
