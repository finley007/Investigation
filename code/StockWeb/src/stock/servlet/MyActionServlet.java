package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.model.MyAction;
import stock.util.CommonUtils;
import stock.util.StockConstants;

public class MyActionServlet extends JSONServlet {
	
	private static final String ACTION_BUY = "Buy";
	
	private static final String ACTION_SELL = "Sell";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String transId = request.getParameter("transId");
			StockManager stockManager = (StockManager)StockAppContext.getBean("stockManager");
			response.getOutputStream().println(createResponse(stockManager.getActionByTransId(transId)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<MyAction> action) throws Exception {
		List<String[]> strs = new ArrayList<String[]>(); 
		if (action == null) {
			action = new ArrayList<MyAction>();
		}
		if (action.size() == 0) {
			action.add(new MyAction());
		}
		for (int i = 0; i < action.size(); i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(addSeparator(action.get(i).getActionId()));
			sb.append(addSeparator(CommonUtils.objToStr(action.get(i).getPrize())));
			sb.append(addSeparator(CommonUtils.objToStr(action.get(i).getQuantity())));
			sb.append(addSeparator(CommonUtils.objToStr(action.get(i).getTime())));
			if (StockConstants.ACTION_TYPE_BUY == action.get(i).getActionType()) {
				sb.append(addSeparator(ACTION_BUY));
			} else {
				sb.append(addSeparator(ACTION_SELL));
			}
			strs.add(sb.toString().split("\\|"));
		}
		return getJSONEnvelop().envelop(strs);
	}

}
