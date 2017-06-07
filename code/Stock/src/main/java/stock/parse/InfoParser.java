package stock.parse;

import stock.model.Stock;


/**
 * @author liuli
 *
 * This interface is used for parse the information and create related Java bean
 */
public interface InfoParser {
	
	public void parseStockInfo(Stock stock, String info) throws Exception;

}
