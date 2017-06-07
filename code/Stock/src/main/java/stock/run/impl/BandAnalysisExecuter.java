package stock.run.impl;

import stock.feed.InfoFeeder;
import stock.feed.http.MyStockInfoFeeder;
import stock.rule.Rule;
import stock.rule.impl.BandRule;
import stock.run.BaseExecuter;

public class BandAnalysisExecuter extends BaseExecuter {

	@Override
	protected InfoFeeder getInfoFeeder() {
		// TODO Auto-generated method stub
		return new MyStockInfoFeeder();
	}

	@Override
	protected Rule getRule() {
		// TODO Auto-generated method stub
		return new BandRule();
	}

	@Override
	protected String getRuleId() {
		// TODO Auto-generated method stub
		return "RULE_HISTORY_3";
	}

}
