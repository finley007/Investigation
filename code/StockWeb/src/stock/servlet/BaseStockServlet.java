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

import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.util.StockConstants;
import stock.vo.MyStockInfo;

public class BaseStockServlet extends HttpServlet {
	
	protected DBQuery query;

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
	
	protected DBQuery getDBQuery() {
		if (query == null) {
			DBConnector connector = new MysqlConnector();
			query = new DBQueryImpl();
			query.setConn(connector);
		}
		return query;
	}
	
	protected MyStockInfo createStockInfo(HttpServletRequest request) {
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
		MyStockInfo stock = new MyStockInfo();
		stock.setCode(code);
		stock.setBuyingPrice(price);
		stock.setQuantity(quantity);
		stock.setBuyingTime(date);
		stock.setTransId(transId);
		return stock;
	}
	
	protected String addSeparator(String str) {
		if ("".equals(str)) {
			return " |";
		} else {
			return str + "|";
		}
	}
}
