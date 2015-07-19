package stock.http.impl;

import stock.http.HTTPQuery;
import stock.vo.Stock;
import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * Call tencent service to get the stock information
 */
public class TencentHTTPQuery extends BaseHTTPQuery implements HTTPQuery {

	@Override
	public StockInfo getStockInfo(Stock stock) throws Exception {
		// TODO Auto-generated method stub
		if (caller == null) {
			throw new Exception("Caller is null");
		}
		String result = caller.callHTTPServ(null);
	}

}
