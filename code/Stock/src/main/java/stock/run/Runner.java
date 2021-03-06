package stock.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Runner {

	private static Logger logger = LoggerFactory.getLogger(Runner.class);

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
			logger.info("*******************************************");
			logger.info("Start to run stock query");
			int totalStockCount = stockManager.getStockCount();
			Date start = new Date();
			Rule rule = new FallTrendRule();
			List<Rule> ruleList = new ArrayList<Rule>();
			ruleList.add(rule);
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
			RuleExecuter.Counter counter = new RuleExecuter.Counter();
			for (int i = 0; i < conditions.length; i++) {
				List<Stock> stockList = stockManager.getStockListByCondition(conditions[i]);
				RuleExecuter executer = new RuleExecuter();
				executer.setFeeder(new MyStockInfoFeeder());
				executer.setHistoryId(historyId);
				executer.setRuleList(ruleList);
				executer.setStockList(stockList);
				executer.setCounter(counter);
				Thread thread = new Thread(executer);
				thread.start();
			}
			while (counter.getTotalCount() < totalStockCount) {
				Thread.sleep(1000);
			}
			int result = counter.getTargetCount() > 0 ? 1 : 0;
			Date end = new Date();
			Long time = end.getTime() - start.getTime();
			stockManager.addRuleExecuteHistory(createCompRuleId(ruleList), historyId, result, time/1000, counter.getTargetCount());
			logger.info("*******************************************");
			logger.info("Complete stock query in: " + time/1000 + " seconds");
			logger.info("Rule: " + ruleList);
			logger.info("History ID: " + historyId);
			logger.info("Target stock count: " + counter.getTargetCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String createCompRuleId(List<Rule> ruleList) {
		String ruleId = "";
		if (ruleList != null && ruleList.size() > 0) {
			for (Rule rule : ruleList) {
				ruleId += rule.getRuleId() + "|";
			}
		}
		return ruleId;
	}
}
