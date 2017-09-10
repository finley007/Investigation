package stock.feed.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.feed.InfoFeeder;
import stock.http.HTTPCaller;
import stock.model.Stock;
import stock.parse.InfoParser;
import stock.parse.impl.tencent.TencentStockFlowParser;

public abstract class HTTPFeeder implements InfoFeeder {

	Logger logger = LoggerFactory.getLogger(HTTPFeeder.class);
	
	protected InfoParser parser;
	
	protected HTTPCaller caller;

	public void feedInfo(Stock myStock) throws Exception {
		// TODO Auto-generated method stub
		caller = createCaller(myStock);
		String result = caller.callHTTPServ(null);
		this.parser.parseStockInfo(myStock, result);
	}
	
	protected abstract String getURL(String stockCode);
	
	protected HTTPCaller createCaller(Stock stock) {
		String url = getURL(stock.getCode());
		logger.debug("Create URL: " + url + " for HTTP feeder: " + this.getClass() + " for stock: " + stock.getLabel());
		HTTPCaller caller = HTTPCaller.getIns(HTTPCaller.Method.Get, url);
		return caller;
	}

}
