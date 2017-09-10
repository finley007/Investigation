package stock.run;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.context.StockAppContext;
import stock.feed.InfoFeeder;
import stock.manager.StockManager;
import stock.model.Stock;
import stock.rule.Rule;


/**
 * 执行者：根据配置的规则筛选股票
 */
public class RuleExecuter implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(RuleExecuter.class);
	
	private static StockManager stockManager = (StockManager)StockAppContext.getBean("stockManager");

	private List<Stock> stockList = new ArrayList<Stock>();

	private String historyId = "";

	private List<Rule> ruleList = new ArrayList<Rule>();

	private InfoFeeder feeder;

	private Counter counter;

	private void init() {
	}
	
	public void run() {
		init();
		excecute();
	}
	
	private void excecute() {
		try {
			List<Stock> resultSet = executeRule();
			solveResult(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void solveResult(List<Stock> resultSet) throws Exception {
		if (this.historyId != null && !"".equals(this.historyId)) {
			for (Stock info : resultSet) {
				logger.info(info.toString());
				stockManager.addRuleExecuteResult(historyId, info.getCode());
//			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://finance.sina.com.cn/realstock/company/" + info.getCode() + "/nc.shtml");
			}
		} else {
			logger.warn("History id not specified");
		}
	}

	private List<Stock> executeRule() throws Exception {
		List<Stock> resultSet = new ArrayList<Stock>();
		if (stockList != null && stockList.size() > 0) {
			for (Stock stock : stockList) {
				countTotal();
				logger.info("Start to solve stock: " + stock.getLabel() + " and code: " + stock.getCode());
				try {
					feeder.feedInfo(stock);
					logger.debug(stock.toJson());
					checkRule(resultSet, stock);
				} catch (Exception e) {
					logger.error("Solve stock error", e);
				}
			}
		}
		logger.info("*******************************************");
		logger.info("The stocks to be checked: ");
		return resultSet;
	}

	private void checkRule(List<Stock> resultSet, Stock stock) throws Exception {
		if (this.ruleList != null && this.ruleList.size() > 0) {
            for (Rule rule : this.ruleList) {
                if (!rule.isSatisfy(stock)) {
                    return;
                }
            }
            resultSet.add(stock);
            countTarget();
        }
	}

	private void countTotal() {
		if (this.counter != null) {
			this.counter.addTotalCount();
		}
	}

	private void countTarget() {
		if (this.counter != null) {
			this.counter.addTargetCount();
		}
	}

	public void clearStockList() {
		this.stockList.clear();
	}

	public void setStockList(List<Stock> list) {
		this.stockList = list;
	}

	public void addStockList(Stock stock) {
		this.stockList.add(stock);
	}

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public InfoFeeder getFeeder() {
		return feeder;
	}

	public void setFeeder(InfoFeeder feeder) {
		this.feeder = feeder;
	}

	public void clearRuleList() {
		this.ruleList.clear();
	}

	public void setRuleList(List<Rule> list) {
		this.ruleList = list;
	}

	public void addRule(Rule rule) {
		this.ruleList.add(rule);
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	static class Counter {

		private int totalCount = 0;

		private int targetCount = 0;

		public synchronized void clearTotalCount() {
			this.totalCount = 0;
		}

		public synchronized void clearTargetCount() {
			this.totalCount = 0;
		}

		public synchronized void addTotalCount() {
			this.totalCount ++;
		}

		public synchronized void addTargetCount() {
			this.targetCount ++;
		}

		public int getTotalCount() {
			return this.totalCount;
		}

		public int getTargetCount() {
			return this.targetCount;
		}

	}

}
