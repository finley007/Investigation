package stock.run;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import stock.feed.InfoFeeder;
import stock.manager.StockManager;
import stock.manager.impl.StockManagerImpl;
import stock.model.Stock;
import stock.rule.Rule;
import stock.util.CommonUtils;
import stock.util.StockConstants;


public abstract class BaseExecuter {
	
	private static Logger logger = LogManager.getLogger(BaseExecuter.class);
	
	private void init() {
	}
	
	public void run() {
		init();
		excecute();
	}
	
	public void excecute() {
		StockManager query = new StockManagerImpl();
		List<Stock> resultSet = new ArrayList<Stock>();
		try {
			executeRule(query, resultSet);
			solveResult(query, resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void solveResult(StockManager query, List<Stock> resultSet) throws Exception {
		String historyId = CommonUtils.createTransactionId(this.getRuleId());
		for (Stock info : resultSet) {
			logger.info(info.toString());
			query.addRuleResult(historyId, info.getCode());
//			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://finance.sina.com.cn/realstock/company/" + info.getCode() + "/nc.shtml");
		}
		query.addRuleHistory(this.getRuleId(), historyId, StockConstants.RESULT_STATUS_SUCCESS, 0l, resultSet.size());
	}

	private void executeRule(StockManager query, List<Stock> resultSet)
			throws Exception {
		List<Stock> stocks = query.getStockList();
		if (stocks != null && stocks.size() > 0) {
			for (Stock stock : stocks) {
				logger.debug("Stock name: " + stock.getName() + " and code: " + stock.getCode());
				InfoFeeder infoFeeder = getInfoFeeder();
				try {
//					infoFeeder.feedInfo(stock);
					logger.debug(stock.toString());
					Rule rule = getRule();
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
	}
	
	protected abstract InfoFeeder getInfoFeeder();
	
	protected abstract Rule getRule();
	
	protected abstract String getRuleId();

}
