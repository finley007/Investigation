package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.util.StockConstants;
import stock.vo.MyStockInfo;

public class BuyStockServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String code = request.getParameter("code");
			MyStockInfo info = getDBQuery().getMyStockByCode(code);
			MyStockInfo currentStockInfo = createStockInfo(request);
			if (info == null) {
				getDBQuery().addMyStock(currentStockInfo);
			} else {
				currentStockInfo.setTransId(info.getTransId());
				getDBQuery().updateMyStock(currentStockInfo, StockConstants.ACTION_TYPE_BUY);
			}
//			response.sendRedirect(request.getContextPath() + "/my_stock.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
