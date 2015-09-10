package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.run.BaseExecuter;
import stock.vo.RuleItemVO;

public class RunRuleServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ruleId = request.getParameter("ruleId");
			RuleItemVO rule = getDBQuery().getRuleItemById(ruleId);
			BaseExecuter executor = (BaseExecuter)RunRuleServlet.class.forName(rule.getImplClz()).newInstance();
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
