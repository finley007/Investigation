package stock.rule;

import stock.model.Stock;

/**
 * @author liuli
 *
 * This interface is used for execute the specified rule for stock information
 */
public interface Rule {
	
	public Boolean isSatisfy(Stock info) throws Exception;

}
