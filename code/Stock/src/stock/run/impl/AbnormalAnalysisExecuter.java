package stock.run.impl;

import stock.feed.InfoFeeder;
import stock.feed.http.MyStockInfoFeeder;
import stock.rule.Rule;
import stock.rule.impl.AbnormalRule;
import stock.run.BaseExecuter;

public class AbnormalAnalysisExecuter extends BaseExecuter {

	@Override
	protected InfoFeeder getInfoFeeder() {
		// TODO Auto-generated method stub
		return new MyStockInfoFeeder();
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
