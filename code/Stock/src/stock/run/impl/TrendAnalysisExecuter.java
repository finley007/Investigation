package stock.run.impl;

import stock.http.HTTPQuery;
import stock.http.impl.MyHTTPQuery;
import stock.rule.Rule;
import stock.rule.impl.TrendRule;
import stock.run.BaseExecuter;

/**
 * @author liuli
 *
 * This executer is used for list all the stocks that have rise trend
 */
public class TrendAnalysisExecuter extends BaseExecuter{
	
	protected HTTPQuery getHTTPQuery() {
		return new MyHTTPQuery();
	}
	
	protected Rule getRule() {
		return new TrendRule();
	}
	
	protected String getRuleId() {
		return "RULE_HISTORY_1";
	}
	
}
