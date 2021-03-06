package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.model.Stock;

public class ShowRuleResultServlet extends JSONServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String hisId = request.getParameter("hisId");
			StockManager stockManager = (StockManager)StockAppContext.getBean("stockManager");
			List<Stock> result = stockManager.getRuleResultByHistoryId(hisId);
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
			info.add(new Stock());
		}
		for (int i = 0; i < info.size(); i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(addSeparator(info.get(i).getCode()));
			sb.append(addSeparator(info.get(i).getName()));
			strs.add(sb.toString().split("\\|"));
		}
		return getJSONEnvelop().envelop(strs);
	}
	
}
