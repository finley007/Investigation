package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.util.StockConstants;

public class SellStockServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			StockManager manager = (StockManager)StockAppContext.getBean("stockManager");
			manager.updateMyStock(createStockInfo(request), StockConstants.ACTION_TYPE_SELL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
