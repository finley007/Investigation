package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.service.StockServiceManager;

public class QueryServlet extends BaseStockServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String serviceCode = request.getParameter("serviceCode");
			String result =StockServiceManager.dispatchRequest(serviceCode, createParams(request));
			System.out.println(result);
			response.getOutputStream().println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
