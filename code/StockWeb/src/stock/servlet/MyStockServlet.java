package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.http.HTTPQuery;
import stock.http.impl.MyHTTPQuery;
import stock.util.CommonUtils;
import stock.vo.MyStockInfo;

public class MyStockServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DBConnector connector = new MysqlConnector();
			DBQuery query = new DBQueryImpl();
			query.setConn(connector);
			List<MyStockInfo> result = query.getMyStock();
			richStockInfo(result);
			response.getOutputStream().println(createResponse(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void richStockInfo(List<MyStockInfo> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (MyStockInfo info : list) {
				HTTPQuery query = new MyHTTPQuery();
				query.richStockInfo(info);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<MyStockInfo> info) {
		List infoList = new ArrayList();
		if (info == null) {
			info = new ArrayList<MyStockInfo>();
		}
		if (info.size() == 0) {
			info.add(new MyStockInfo());
		}
		StringBuffer sb = new StringBuffer("{ \"data\": [");
		for (int i = 0; i < info.size(); i++) {
			sb.append(" [ " );
			sb.append(addQuotes(info.get(i).getTransId()));
			sb.append(addQuotes(info.get(i).getCode()));
			sb.append(addQuotes(info.get(i).getName()));
			sb.append(addQuotes(CommonUtils.objToStr(info.get(i).getBuyingPrice())));
			sb.append(addQuotes(CommonUtils.objToStr(info.get(i).getCurrentPrice())));
			sb.append(addQuotes(CommonUtils.objToStr(info.get(i).getQuantity())));
			sb.append(addQuotes(CommonUtils.objToStr(info.get(i).getProfit())));
			sb.append(addQuotes(CommonUtils.objToStr(info.get(i).getProfitRate()) + "%"));
			sb.append(addQuotes(CommonUtils.objToStr(info.get(i).getBuyingTime())));
			sb.append(addQuotes(""));
			sb.append(addQuotes(CommonUtils.objToStr(info.get(i).getIsMonitor()), true));
			if (i == info.size() - 1) {
				sb.append(" ] ");
			} else {
				sb.append(" ], ");
			}
		}
		sb.append(" ] }");
		String result = sb.toString();
		System.out.println(result);
		return result;
	}
	
	private String addQuotes(String str, boolean isLast) {
		if (isLast) {
			return "\"" + str + "\"";
		} else {
			return addQuotes(str);
		}
	}
	
	private String addQuotes(String str) {
		return "\"" + str + "\"" + ",";
	}
}
