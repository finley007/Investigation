package stock.service.impl;

import java.util.Map;

import stock.context.StockAppContext;
import stock.manager.StockManager;
import stock.service.ParamName;
import stock.service.StockService;
import stock.util.JsonHelper;
import stock.vo.TransInfoVO;

public class QueryTransInfo implements StockService {

	@Override
	public String request(Map params) throws Exception {
		// TODO Auto-generated method stub
		String stockCode = params.get(ParamName.STOCK_CODE) != null ? 
				params.get(ParamName.STOCK_CODE).toString() : "";
		StockManager manager = (StockManager)StockAppContext.getBean("stockManager");
		return "";
//		TransInfoVO transInfo = query.queryTransInfo(stockCode);
//		return JsonHelper.toJson(transInfo);
	}

}
