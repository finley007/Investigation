package stock.service.impl;

import java.util.HashMap;
import java.util.Map;

import stock.service.ParamName;
import stock.service.StockService;
import stock.util.CommonUtils;
import stock.util.JsonHelper;
import stock.util.StockUtils;

/**
 * 
 * Query all the stock information based on the transaction id
 * 
 * @author liuli
 *
 */
public class QueryTransactionFee implements StockService {

	@Override
	public String request(Map params) throws Exception {
		// TODO Auto-generated method stub
		if (params.get(ParamName.TRANSACTION_ACTION) == null) {
			throw new Exception("Parameter: " + ParamName.TRANSACTION_ACTION + " is required!!");
		}
		if (params.get(ParamName.TRANSACTION_QUANTITY) == null) {
			throw new Exception("Parameter: " + ParamName.TRANSACTION_QUANTITY + " is required!!");
		}
		if (params.get(ParamName.STOCK_PRICE) == null) {
			throw new Exception("Parameter: " + ParamName.STOCK_PRICE + " is required!!");
		}
		Integer action = 0;
		if (!"".equals(params.get(ParamName.TRANSACTION_ACTION).toString())) {
			action = Integer.valueOf(params.get(ParamName.TRANSACTION_ACTION).toString());
		}
		Integer quantity = 0;
		if (!"".equals(params.get(ParamName.TRANSACTION_QUANTITY).toString())) {
			quantity =	Integer.valueOf(params.get(ParamName.TRANSACTION_QUANTITY).toString());
		}
		Double price = Double.valueOf(params.get(ParamName.STOCK_PRICE).toString());
		Double fee = CommonUtils.round(StockUtils.caculateFee(action, quantity, price), 2);
		Double totalPrice = CommonUtils.round(quantity * price, 2);
		Map map = new HashMap();
		map.put("totalPrice", totalPrice);
		map.put("fee", fee);
		return JsonHelper.toJson(map);
	}

}
