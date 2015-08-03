package stock.http;

import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * This interface is used for get stack related information by HTTP query
 */
public interface HTTPQuery {
	
	public void richStockInfo(StockInfo stock) throws Exception;
	
	public void setHTTPCaller(HTTPCaller caller);
	
	public HTTPCaller getHTTPCaller();

}
