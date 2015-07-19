package stock.http.impl;

import stock.analysis.TencentStockInfoParser;
import stock.http.HTTPQuery;
import stock.vo.Stock;
import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * Call tencent service to get the stock information
 */
public class TencentHTTPQuery extends BaseHTTPQuery implements HTTPQuery {
	
	public TencentHTTPQuery() {
		this.parser = new TencentStockInfoParser();
	}

	@Override
	public StockInfo getStockInfo(Stock stock) throws Exception {
		// TODO Auto-generated method stub
		if (caller == null) {
			throw new Exception("Caller is null");
		}
		String result = caller.callHTTPServ(null);
		return this.parser.parseStockInfo(stock, result);
	}

}
