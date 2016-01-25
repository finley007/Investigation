package stock.service;

import java.util.Map;

import stock.service.impl.QueryStockInfo;
import stock.service.impl.QueryTransInfo;
import stock.service.impl.QueryTransactionFee;

public class StockServiceDispatcher {
	
	public static String dispatchRequest(String serviceCode, Map params) throws Exception {
		StockService service = null;
		if (ServiceCode.QUERY_STOCK_INFO.equals(serviceCode)) {
			service = new QueryStockInfo();
		} else if (ServiceCode.QUERY_TRANSACTION_FEE.equals(serviceCode)) {
			service = new QueryTransactionFee();
		} else if (ServiceCode.QUERY_TRANSACTION_INFORMATION.equals(serviceCode)) {
			service = new QueryTransInfo();
		} else {
			return "";
		}
		return service.request(params);
	}

}
