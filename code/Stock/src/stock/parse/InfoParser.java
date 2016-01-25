package stock.parse;

import stock.model.MyStock;


/**
 * @author liuli
 *
 * This interface is used for parse the information and create related Java bean
 */
public interface InfoParser {
	
	public void parseStockInfo(MyStock stock, String info) throws Exception;

}
