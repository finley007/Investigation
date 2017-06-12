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

	private Rule rule;

	private InfoFeeder feeder;

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
				logger.debug("Stock name: " + stock.getName() + " and code: " + stock.getCode());
				try {
					feeder.feedInfo(stock);
					logger.debug(stock.toString());
					if (rule.isSatisfy(stock)) {
						resultSet.add(stock);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Ths stock: " + stock.getCode() + " are failed to obtain");
				}
			}
		}
		logger.info("*******************************************");
		logger.info("The stocks to be checked: ");
		return resultSet;
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

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public InfoFeeder getFeeder() {
		return feeder;
	}

	public void setFeeder(InfoFeeder feeder) {
		this.feeder = feeder;
	}

}
