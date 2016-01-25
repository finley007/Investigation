package stock.run.impl;

import stock.feed.InfoFeeder;
import stock.feed.http.MyStockInfoFeeder;
import stock.rule.Rule;
import stock.rule.impl.FallTrendRule;
import stock.run.BaseExecuter;

/**
 * @author liuli
 *
 * This executer is used for list all the stocks that have rise trend
 */
public class FallTrendAnalysisExecuter extends BaseExecuter{
	
	protected InfoFeeder getInfoFeeder() {
		return new MyStockInfoFeeder();
	}
	
	protected Rule getRule() {
		return new FallTrendRule();
	}
	
	protected String getRuleId() {
		return "RULE_HISTORY_2";
	}
	
}
