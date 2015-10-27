package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.util.CommonUtils;
import stock.util.StockConstants;
import stock.vo.ActionVO;

public class MyActionServlet extends JSONServlet {
	
	private static final String ACTION_BUY = "Buy";
	
	private static final String ACTION_SELL = "Sell";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String transId = request.getParameter("transId");
			response.getOutputStream().println(createResponse(getDBQuery().getActionByTransId(transId)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<ActionVO> info) throws Exception {
		List<String[]> strs = new ArrayList<String[]>(); 
		if (info == null) {
			info = new ArrayList<ActionVO>();
		}
		if (info.size() == 0) {
			info.add(new ActionVO());
		}
		for (int i = 0; i < info.size(); i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(addSeparator(info.get(i).getActionId()));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getPrize())));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getQuantity())));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getTime())));
			if (StockConstants.ACTION_TYPE_BUY == info.get(i).getType()) {
				sb.append(addSeparator(ACTION_BUY));
			} else {
				sb.append(addSeparator(ACTION_SELL));
			}
			strs.add(sb.toString().split("\\|"));
		}
		return getJSONEnvelop().envelop(strs);
	}

}
