package stock.service.impl;

import java.util.Map;

import stock.db.DBQuery;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
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
		DBQuery query = new DBQueryImpl();
		query.setConn(new MysqlConnector());
		TransInfoVO transInfo = query.queryTransInfo(stockCode);
		return JsonHelper.toJson(transInfo);
	}

}
