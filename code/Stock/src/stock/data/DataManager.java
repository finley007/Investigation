package stock.data;

import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * This interface is used for solving the stock information
 */
public interface DataManager {
	
	public void handle(StockInfo info) throws Exception;

}
