package stock.feed.http;

import stock.feed.InfoFeeder;
import stock.http.HTTPCaller;
import stock.model.Stock;
import stock.parse.InfoParser;

public abstract class HTTPFeeder implements InfoFeeder {
	
	protected InfoParser parser;
	
	protected HTTPCaller caller;

	public void feedInfo(Stock myStock) throws Exception {
		// TODO Auto-generated method stub
		if (caller == null) {
			caller = createCaller(myStock, HTTPCaller.Method.Get);
		}
		String result = caller.callHTTPServ(null);
		this.parser.parseStockInfo(myStock, result);
	}
	
	protected abstract String getURL(String stockCode);
	
	protected HTTPCaller createCaller(Stock stock, HTTPCaller.Method method) {
		String url = getURL(stock.getCode());
		HTTPCaller caller = HTTPCaller.getIns(method, url);
		return caller;
	}

}
