package stock.envelop.json.impl;

import java.util.List;

import stock.envelop.json.JSONEnvelop;

public class JTreeJSONEnvelop implements JSONEnvelop {

	@Override
	public String envelop(List<String[]> info) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("{ 'data' : [{ 'text' : 'All my stocks', 'state' : { 'opened' : true }, 'children' : [");
		if (info != null && info.size() > 0) {
			for (int i = 0; i < info.size(); i++) {
				sb.append("{'text' : '" + info.get(i)[0] + "(" + info.get(i)[1] + ")', " +
						"'icon' : 'glyphicon glyphicon-leaf', 'id' : '" + info.get(i)[0] + "'}");
				if (i != info.size() - 1) {
					sb.append(",");
				}
			}
		}
		sb.append("]}, ]}");
		return sb.toString();
	}

}
