package stock.service.impl;

import java.util.Map;

import stock.context.StockAppContext;
import stock.feed.InfoFeeder;
import stock.feed.http.tencent.TencentHTTPRuntimeFeeder;
import stock.manager.StockManager;
import stock.model.MyStock;
import stock.service.ParamName;
import stock.service.StockService;
import stock.util.JsonHelper;

/**
 * 
 * Query all the stock information based on the transaction id
 * 
 * @author liuli
 *
 */
public class QueryStockInfo implements StockService {

	@Override
	public String request(Map params) throws Exception {
		// TODO Auto-generated method stub
		if (params.get(ParamName.TRANSACTION_ID) == null) {
			throw new Exception("Parameter: " + ParamName.TRANSACTION_ID + " is required!!");
		}
		String transId = params.get(ParamName.TRANSACTION_ID).toString();
		if ("".equals(transId)) {
			return JsonHelper.createEmptyJson();
		}
		StockManager manager = (StockManager)StockAppContext.getBean("stockManager");
		MyStock myStock = manager.getMyStockByTransId(transId);
		InfoFeeder feeder = new TencentHTTPRuntimeFeeder();
		feeder.feedInfo(myStock.getStock());
		return myStock.getStock().toJson();
	}

}
