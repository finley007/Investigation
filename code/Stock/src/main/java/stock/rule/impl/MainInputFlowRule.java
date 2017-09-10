package stock.rule.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.model.Stock;
import stock.rule.Rule;

public class MainInputFlowRule extends BaseRule implements Rule {

	Logger logger = LoggerFactory.getLogger(MainInputFlowRule.class);
	
	private static final Double level = 2000.0;

	public Boolean isSatisfy(Stock info) throws Exception {
		// TODO Auto-generated method stub
		if (info.getMainInflow() > 1.5 * info.getMainOutflow()
				&& info.getMainInflow() > level) {
			return true;
		} else {
			return false;
		}
	}

	public String getRuleId() {
		return "RULE_RUNTIME_1";
	}
}
