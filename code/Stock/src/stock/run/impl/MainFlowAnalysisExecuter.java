package stock.run.impl;

import stock.http.HTTPQuery;
import stock.http.impl.tencent.TencentHTTPFlowQuery;
import stock.rule.Rule;
import stock.rule.impl.MainInputFlowRule;
import stock.run.BaseExcecuter;

/**
 * @author liuli
 *
 * This executer is used for list all the stocks that have big main inflow
 */
public class MainFlowAnalysisExecuter extends BaseExcecuter {
	
	protected HTTPQuery getHTTPQuery() {
		return new TencentHTTPFlowQuery();
	}
	
	protected Rule getRule() {
		return new MainInputFlowRule();
	}
	
	protected String getRuleId() {
		return "RULE_RUNTIME_1";
	}
	
}
