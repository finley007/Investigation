package stock.run.impl;

import stock.http.HTTPQuery;
import stock.http.impl.MyHTTPQuery;
import stock.rule.Rule;
import stock.rule.impl.FallTrendRule;
import stock.run.BaseExecuter;

/**
 * @author liuli
 *
 * This executer is used for list all the stocks that have rise trend
 */
public class FallTrendAnalysisExecuter extends BaseExecuter{
	
	protected HTTPQuery getHTTPQuery() {
		return new MyHTTPQuery();
	}
	
	protected Rule getRule() {
		return new FallTrendRule();
	}
	
	protected String getRuleId() {
		return "RULE_HISTORY_2";
	}
	
}
