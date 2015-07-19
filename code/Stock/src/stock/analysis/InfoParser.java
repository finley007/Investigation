package stock.analysis;

import stock.vo.Stock;
import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * This interface is used for parse the information and create related Java bean
 */
public interface InfoParser {
	
	public StockInfo parseStockInfo(Stock stock, String info) throws Exception;

}
