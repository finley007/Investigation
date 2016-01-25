package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.model.MyStock;
import stock.util.StockConstants;

public class BuyStockServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			StockManager manager = (StockManager)StockAppContext.getBean("stockManager");
			String code = request.getParameter("code");
			
			MyStock myStock = manager.getMyCurrentStockByCode(code);
			MyStock currentStock = createStockInfo(request);
			if (myStock == null) {
				manager.addMyStock(currentStock);
			} else {
				currentStock.setTransactionId(myStock.getTransactionId());
				manager.updateMyStock(currentStock, StockConstants.ACTION_TYPE_BUY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
