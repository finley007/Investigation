package stock.http.impl;

import stock.http.HTTPCaller;
import stock.http.HTTPQuery;
import stock.vo.Stock;
import stock.vo.StockInfo;

public class BaseHTTPQuery implements HTTPQuery {
	
	public HTTPCaller getHTTPCaller() {
		return caller;
	}

	public void setHTTPCaller(HTTPCaller caller) {
		this.caller = caller;
	}

	protected HTTPCaller caller;

	@Override
	public StockInfo getStockInfo(Stock stock) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
