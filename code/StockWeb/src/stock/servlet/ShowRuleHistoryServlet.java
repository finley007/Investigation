package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.util.CommonUtils;
import stock.util.StockConstants;
import stock.vo.RuleRunHistoryVO;

public class ShowRuleHistoryServlet extends JSONServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String ruleId = request.getParameter("ruleId");
//			List<RuleRunHistoryVO> result = getDBQuery().getRuleRunHistoryByRuleId(ruleId);
//			response.getOutputStream().println(createResponse(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<RuleRunHistoryVO> info) throws Exception {
		List<String[]> strs = new ArrayList<String[]>(); 
		if (info == null) {
			info = new ArrayList<RuleRunHistoryVO>();
		}
		if (info.size() == 0) {
			info.add(new RuleRunHistoryVO());
		}
		for (int i = 0; i < info.size(); i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(addSeparator(info.get(i).getId()));
			if (info.get(i).getTime() != null) {
				sb.append(addSeparator(StockConstants.sdf_time.format(info.get(i).getTime())));
			} else {
				sb.append(addSeparator(""));
			}
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getStockNum())));
			strs.add(sb.toString().split("\\|"));
		}
		return getJSONEnvelop().envelop(strs);
	}
	
}
