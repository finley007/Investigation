package stock.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.vo.RuleItemVO;

public class SaveRuleServlet extends BaseStockServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String type = request.getParameter("type");
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String implClz = request.getParameter("implClz");
			String desp = request.getParameter("desp");
			RuleItemVO vo = new RuleItemVO();
			vo.setType(Integer.valueOf(type));
			vo.setId(id);
			vo.setName(name);
			vo.setImplClz(implClz);
			vo.setDesp(desp);
//			getDBQuery().saveOrUpdateRuleItem(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
