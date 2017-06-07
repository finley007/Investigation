package stock.run.impl;

import stock.feed.InfoFeeder;
import stock.feed.http.tencent.TencentHTTPFlowFeeder;
import stock.rule.Rule;
import stock.rule.impl.MainInputFlowRule;
import stock.run.BaseExecuter;

/**
 * @author liuli
 *
 * This executer is used for list all the stocks that have big main inflow
 */
public class MainFlowAnalysisExecuter extends BaseExecuter {
	
	protected InfoFeeder getInfoFeeder() {
		return new TencentHTTPFlowFeeder();
	}
	
	protected Rule getRule() {
		return new MainInputFlowRule();
	}
	
	protected String getRuleId() {
		return "RULE_RUNTIME_1";
	}
	
}
