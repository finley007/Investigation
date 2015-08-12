package stock.http.impl;

import stock.http.HTTPCaller;
import stock.http.HTTPQuery;
import stock.parse.InfoParser;
import stock.vo.StockInfo;

public abstract class BaseHTTPQuery implements HTTPQuery {
	
	protected InfoParser parser;
	
	public HTTPCaller getHTTPCaller() {
		return caller;
	}

	public void setHTTPCaller(HTTPCaller caller) {
		this.caller = caller;
	}

	protected HTTPCaller caller;

	public void richStockInfo(StockInfo stock) throws Exception {
		// TODO Auto-generated method stub
		if (caller == null) {
			caller = createGetCaller(stock);
		}
		String result = caller.callHTTPServ(null);
		this.parser.parseStockInfo(stock, result);
	}
	
	protected abstract String getURL(StockInfo stock);
	
	protected HTTPCaller createGetCaller(StockInfo stock) {
		HTTPCaller caller = HTTPCaller.getIns(HTTPCaller.Method.Get);
		String url = getURL(stock);
		caller.setUrl(url);
		return caller;
	}
	
	protected HTTPCaller createPostCaller(StockInfo stock) {
		HTTPCaller caller = HTTPCaller.getIns(HTTPCaller.Method.Post);
		String url = getURL(stock);
		caller.setUrl(url);
		return caller;
	}

}
