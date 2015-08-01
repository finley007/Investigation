package stock.db;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import stock.db.connect.MysqlConnector;
import stock.vo.MyStockInfo;
import stock.vo.Stock;
import stock.vo.StockInfo;

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
			Assert.assertEquals(stocks.size(),791);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetMyStock() {
		try {
			List<MyStockInfo> stocks = query.getMyStock();
			Assert.assertEquals(stocks.size(),3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetMyStockByCode() {
		try {
			StockInfo stock = query.getMyStockByCode("600731");
			Assert.assertEquals(stock.getStock().getCode(),"600731");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddMyStock() {
		try {
			MyStockInfo stock = new MyStockInfo(new Stock("aa"));
			stock.setBuyingPrice(22.6);
			stock.setQuantity(800);
			stock.setBuyingTime(new Date());
			query.addMyStock(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
