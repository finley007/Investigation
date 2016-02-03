package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.model.RuleItem;
import stock.run.BaseExecuter;

public class RunRuleServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ruleId = request.getParameter("ruleId");
			String ruleType = request.getParameter("ruleType");
			RuleItem ruleItem = new RuleItem();
			ruleItem.setId(ruleId);
			ruleItem.setType(ruleType);
			StockManager stockManager = (StockManager)StockAppContext.getBean("stockManager");
			ruleItem = stockManager.getRuleItemByPK(ruleItem);
			BaseExecuter executor = (BaseExecuter)RunRuleServlet.class.forName(ruleItem.getImplClass()).newInstance();
			executor.excecute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
