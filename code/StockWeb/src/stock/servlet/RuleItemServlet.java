package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.model.RuleItem;

public class RuleItemServlet extends JSONServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer type = Integer.valueOf(request.getParameter("ruleType"));
			StockManager stockManager = (StockManager)StockAppContext.getBean("stockManager");
			List<RuleItem> result = stockManager.getRuleItemByType(type);
			response.getOutputStream().println(createResponse(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<RuleItem> info) throws Exception {
		List<String[]> strs = new ArrayList<String[]>(); 
		for (int i = 0; i < info.size(); i++) {
			StringBuffer sb = new StringBuffer("");
			sb.append(addSeparator(info.get(i).getId()));
			sb.append(addSeparator(info.get(i).getName()));
			sb.append(addSeparator(info.get(i).getImplClass()));
			sb.append(addSeparator(""));
			sb.append(addSeparator(""));
			strs.add(sb.toString().split("\\|"));
		}
		return getJSONEnvelop().envelop(strs);
	}
	
}
