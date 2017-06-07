package stock.context;

import java.util.List;

import org.junit.Test;

import stock.manager.StockManager;
import stock.model.MyStock;

public class StockAppContextTest {

	@Test
	public void testGetBean() {
		StockManager manager = (StockManager)StockAppContext.getBean("stockManager");
		try {
			List<MyStock> stocks = manager.getMyCurrentStock();
			System.out.println(stocks.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
