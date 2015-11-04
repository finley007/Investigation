package stock.run.impl;

import stock.http.HTTPQuery;
import stock.http.impl.MyHTTPQuery;
import stock.rule.Rule;
import stock.rule.impl.AbnormalRule;
import stock.run.BaseExecuter;

public class AbnormalAnalysisExecuter extends BaseExecuter {

	@Override
	protected HTTPQuery getHTTPQuery() {
		// TODO Auto-generated method stub
		return new MyHTTPQuery();
	}

	@Override
	protected Rule getRule() {
		// TODO Auto-generated method stub
		return new AbnormalRule();
	}

	@Override
	protected String getRuleId() {
		// TODO Auto-generated method stub
		return "RULE_HISTORY_4";
	}

}
