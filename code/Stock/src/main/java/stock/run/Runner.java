package stock.run;

import stock.analysis.StockAnalysis;
import stock.analysis.impl.ShowDiagram;
import stock.analysis.impl.StatisticAnalysis;
import stock.context.StockAppContext;
import stock.feed.http.MyStockInfoFeeder;
import stock.manager.StockManager;
import stock.model.Stock;
import stock.rule.Rule;
import stock.rule.impl.FallTrendRule;
import stock.rule.impl.TrendRule;
import stock.util.CommonUtils;

import java.util.List;


public class Runner {

	private static StockManager stockManager = (StockManager) StockAppContext.getBean("stockManager");

	public static void main(String[] args) {
		executeRule();
//		checkStocks();
//		doAnalysis();
	}

	private static void doAnalysis() {
		StockAnalysis analysis = new StatisticAnalysis();
		try {
			int size = 10;
			int init = 7;
			analysis.doAnalysis("20150819120824878RULE_HISTORY_1", init * size + 1, size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void checkStocks() {
		StockAnalysis analysis = new ShowDiagram();
		try {
			int size = 0;
			int init = 0;
			analysis.doAnalysis("20150908110903337RULE_HISTORY_1", init * size + 1, size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void executeRule() {
		try {
			Rule rule = new TrendRule();
			String historyId = CommonUtils.createTransactionId(rule.getRuleId());
			String[] conditions = new String[]{
					"where code like '%0'",
					"where code like '%1'",
					"where code like '%2'",
					"where code like '%3'",
					"where code like '%4'",
					"where code like '%5'",
					"where code like '%6'",
					"where code like '%7'",
					"where code like '%8'",
					"where code like '%9'"
			};
			for (int i = 0; i < conditions.length; i++) {
				List<Stock> stockList = stockManager.getStockListByCondition(conditions[i]);
				RuleExecuter executer = new RuleExecuter();
				executer.setFeeder(new MyStockInfoFeeder());
				executer.setHistoryId(historyId);
				executer.setRule(rule);
				executer.setStockList(stockList);
				Thread thread = new Thread(executer);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
