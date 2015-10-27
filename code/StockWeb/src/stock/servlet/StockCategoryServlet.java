package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.envelop.json.JSONEnvelop;
import stock.envelop.json.impl.JTreeJSONEnvelop;
import stock.vo.MyStockInfo;
import stock.vo.Stock;

public class StockCategoryServlet extends JSONServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Stock> result = getDBQuery().getStockCategory();
			response.getOutputStream().println(createResponse(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<Stock> info) throws Exception {
		List<String[]> strs = new ArrayList<String[]>(); 
		if (info == null) {
			info = new ArrayList<Stock>();
		}
		if (info.size() == 0) {
			info.add(new MyStockInfo());
		}
		for (int i = 0; i < info.size(); i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(addSeparator(info.get(i).getCode()));
			sb.append(addSeparator(info.get(i).getName()));
			strs.add(sb.toString().split("\\|"));
		}
		return getJSONEnvelop().envelop(strs);
	}
	
	protected JSONEnvelop getJSONEnvelop() {
		return new JTreeJSONEnvelop();
	} 
	
}
