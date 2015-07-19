package stock.rule;

import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * This interface is used for execute the specified rule for stock information
 */
public interface Rule {
	
	public Boolean isSatisfy(StockInfo info) throws Exception;

}
