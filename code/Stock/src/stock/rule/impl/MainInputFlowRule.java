package stock.rule.impl;

import stock.rule.Rule;
import stock.vo.StockInfo;

public class MainInputFlowRule implements Rule {
	
	private static final Double level = 2000.0;

	public Boolean isSatisfy(StockInfo info) throws Exception {
		// TODO Auto-generated method stub
		if (info.getMainInflow() > 1.5 * info.getMainOutflow()
				&& info.getMainInflow() > level) {
			return true;
		} else {
			return false;
		}
	}

}
