package stock.envelop.json.impl;

import java.util.ArrayList;
import java.util.List;

import stock.envelop.json.JSONEnvelop;

public class DataTableJSONEnvelop implements JSONEnvelop {

	@Override
	public String envelop(List<String[]> info) throws Exception {
		// TODO Auto-generated method stub
		List infoList = new ArrayList();
		StringBuffer sb = new StringBuffer("{ \"data\": [");
		if (info != null && info.size() > 0) {
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
		}
		sb.append(" ] }");
		String result = sb.toString();
		System.out.println(result);
		return result;
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
