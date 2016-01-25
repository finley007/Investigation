package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.model.MyStock;
import stock.util.CommonUtils;

public class MyStockServlet extends JSONServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String stockCode = request.getParameter("stockCode");
			StockManager stockManager = (StockManager)StockAppContext.getBean("stockManager");
			List<MyStock> result = stockManager.getMyCurrentStock();
			response.getOutputStream().println(createResponse(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<MyStock> info) throws Exception {
		List<String[]> strs = new ArrayList<String[]>(); 
		if (info == null) {
			info = new ArrayList<MyStock>();
		}
		if (info.size() == 0) {
			info.add(new MyStock());
		}
		for (int i = 0; i < info.size(); i++) {
			StringBuffer sb = new StringBuffer();
			MyStock myStock = info.get(i);
			sb.append(addSeparator(myStock.getTransactionId()));
			sb.append(addSeparator(myStock.getStock().getCode()));
			sb.append(addSeparator(myStock.getStock().getName()));
			sb.append(addSeparator(CommonUtils.objToStr(myStock.getBuyPrice())));
			sb.append(addSeparator(CommonUtils.objToStr(myStock.getStock().getCurrentPrice())));
			sb.append(addSeparator(CommonUtils.objToStr(myStock.getQuantity())));
			sb.append(addSeparator(CommonUtils.objToStr(myStock.getProfit())));
			sb.append(addSeparator(CommonUtils.objToStr(myStock.getProfitRate()) + "%"));
			sb.append(addSeparator(CommonUtils.objToStr(myStock.getOpenTime())));
			sb.append(addSeparator(""));
			sb.append(addSeparator(CommonUtils.objToStr(myStock.getIsMonitor())));
			strs.add(sb.toString().split("\\|"));
		}
		return getJSONEnvelop().envelop(strs);
	}
	
}
