package stock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stock.http.HTTPQuery;
import stock.http.impl.MyHTTPQuery;
import stock.util.CommonUtils;
import stock.vo.MyStockInfo;

public class MyStockServlet extends DataTableServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<MyStockInfo> result = getDBQuery().getMyStock();
			richStockInfo(result);
			response.getOutputStream().println(createResponse(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void richStockInfo(List<MyStockInfo> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (MyStockInfo info : list) {
				HTTPQuery query = new MyHTTPQuery();
				query.richStockInfo(info);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	String createResponse(List<MyStockInfo> info) {
		List<String[]> strs = new ArrayList<String[]>(); 
		if (info == null) {
			info = new ArrayList<MyStockInfo>();
		}
		if (info.size() == 0) {
			info.add(new MyStockInfo());
		}
		for (int i = 0; i < info.size(); i++) {
			StringBuffer sb = new StringBuffer();
			sb.append(addSeparator(info.get(i).getTransId()));
			sb.append(addSeparator(info.get(i).getCode()));
			sb.append(addSeparator(info.get(i).getName()));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getBuyingPrice())));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getCurrentPrice())));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getQuantity())));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getProfit())));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getProfitRate()) + "%"));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getBuyingTime())));
			sb.append(addSeparator(""));
			sb.append(addSeparator(CommonUtils.objToStr(info.get(i).getIsMonitor())));
			strs.add(sb.toString().split("\\|"));
		}
		return createDataTableInfo(strs);
	}
	
}
