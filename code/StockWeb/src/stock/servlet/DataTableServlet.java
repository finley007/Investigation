package stock.servlet;

import java.util.ArrayList;
import java.util.List;

public class DataTableServlet extends BaseStockServlet {

	protected String createDataTableInfo(List<String[]> info) {
		List infoList = new ArrayList();
		StringBuffer sb = new StringBuffer("{ \"data\": [");
		for (int i = 0; i < info.size(); i++) {
			String[] strs = info.get(i);
			sb.append(" [ " );
			for (int j = 0; j < strs.length; j++) {
				if (j == strs.length - 1) {
					sb.append(addQuotes(strs[j], true));
				} else {
					sb.append(addQuotes(strs[j]));
				}
			}
			if (i == info.size() - 1) {
				sb.append(" ] ");
			} else {
				sb.append(" ], ");
			}
		}
		sb.append(" ] }");
		String result = sb.toString();
		System.out.println(result);
		return result;
	}
	
	protected String addSeparator(String str) {
		if ("".equals(str)) {
			return " |";
		} else {
			return str + "|";
		}
	}
	
	private String addQuotes(String str, boolean isLast) {
		if (isLast) {
			return "\"" + str + "\"";
		} else {
			return addQuotes(str);
		}
	}
	
	private String addQuotes(String str) {
		return "\"" + str + "\"" + ",";
	}
	
}
