package stock.servlet;

import stock.envelop.json.JSONEnvelop;
import stock.envelop.json.impl.DataTableJSONEnvelop;



public class JSONServlet extends BaseStockServlet {
	
	protected JSONEnvelop getJSONEnvelop() {
		return new DataTableJSONEnvelop();
	}

}
