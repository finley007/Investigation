package stock.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.model.MyStock;
import stock.model.Stock;
import stock.util.StockConstants;

public class BaseStockServlet extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	protected Map createParams(HttpServletRequest request) {
		Map<String, String> requestMap = new HashMap<String, String>();
		for(Enumeration e = request.getParameterNames(); e.hasMoreElements(); ){
			String key = (String)e.nextElement();
			requestMap.put(key, request.getParameter(key));
		}
		return requestMap;
	}
	
	protected MyStock createStockInfo(HttpServletRequest request) {
		String transId = request.getParameter("transId");
		String code = request.getParameter("code");
		Double price = Double.valueOf(request.getParameter("price"));
		Integer quantity = Integer.valueOf(request.getParameter("quantity"));
		String time = request.getParameter("time");
		Date date = new Date();
		try {
			date = StockConstants.sdf_time.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MyStock myStock = new MyStock();
		Stock stock = new Stock(code);
		myStock.setStock(stock);
		myStock.setBuyPrice(price);
		myStock.setQuantity(quantity);
		myStock.setOpenTime(date);
		myStock.setTransactionId(transId);
		return myStock;
	}
	
	protected String addSeparator(String str) {
		if ("".equals(str)) {
			return " |";
		} else {
			return str + "|";
		}
	}
}
