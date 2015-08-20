package stock.service.impl;

import java.util.Map;

import stock.db.DBQuery;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.http.HTTPQuery;
import stock.http.impl.MyHTTPQuery;
import stock.service.ParamName;
import stock.service.StockService;
import stock.vo.MyStockInfo;

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
		DBQuery query = new DBQueryImpl();
		query.setConn(new MysqlConnector());
		MyStockInfo stockInfo = query.getMyStockByTransId(transId);
		HTTPQuery httpQuery = new MyHTTPQuery();
		httpQuery.richStockInfo(stockInfo);
		return stockInfo.toJson();
	}

}
