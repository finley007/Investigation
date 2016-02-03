package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.model.RuleItem;

public class DeleteRuleServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String type = request.getParameter("type");
			String id = request.getParameter("id");
			RuleItem ruleItem = new RuleItem();
			ruleItem.setType(type);
			ruleItem.setId(id);
			StockManager manager = (StockManager)StockAppContext.getBean("stockManager");
			manager.deleteRuleItem(ruleItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
