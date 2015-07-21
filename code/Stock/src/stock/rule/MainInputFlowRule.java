package stock.rule;

import stock.vo.StockInfo;

public class MainInputFlowRule implements Rule {

	public Boolean isSatisfy(StockInfo info) throws Exception {
		// TODO Auto-generated method stub
		if (info.getMainInflow() > 2 * info.getMainOutflow()) {
			return true;
		} else {
			return false;
		}
	}

}
