package stock.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.vo.MyStockInfo;
import stock.vo.Stock;
import stock.vo.StockInfo;
import stock.vo.TransInfoVO;

public class DBQueryImplTest {
	
	public static final SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
	
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
			StockInfo stock = query.getMyStockByTransId("20150731220735636sh600278");
			Assert.assertEquals(stock.getCode(),"sh600278");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddMyStock() {
		try {
			MyStockInfo stock = new MyStockInfo();
			stock.setCode("aa");
			stock.setBuyingPrice(22.6);
			stock.setQuantity(800);
			stock.setBuyingTime(new Date());
			query.addMyStock(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClearCalendar() {
		try {
			Date start = sdf_date.parse("2015-09-02"); 
			Date end = sdf_date.parse("2015-09-03"); 
			query.clearCalendar(start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertCalendar() {
		try {
			Date date = sdf_date.parse("2015-09-08"); 
			query.insertCalendar(date, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryTransInfo() {
		try {
			TransInfoVO vo = query.queryTransInfo("");
			System.out.println(vo.getAvgProfit());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
