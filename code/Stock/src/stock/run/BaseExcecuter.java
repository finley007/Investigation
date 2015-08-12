package stock.run;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.http.HTTPQuery;
import stock.rule.Rule;
import stock.util.StockConstants;
import stock.util.StockUtils;
import stock.vo.Stock;
import stock.vo.StockInfo;


public abstract class BaseExcecuter {
	
	private static Logger logger = LogManager.getLogger(BaseExcecuter.class);
	
	private void init() {
	}
	
	public void run() {
		init();
		excecute();
	}
	
	public void excecute() {
		DBConnector connector = new MysqlConnector();
		DBQuery query = new DBQueryImpl();
		query.setConn(connector);
		List<StockInfo> resultSet = new ArrayList<StockInfo>();
		try {
			executeRule(query, resultSet);
			solveResult(query, resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void solveResult(DBQuery query, List<StockInfo> resultSet) throws Exception {
		String historyId = StockUtils.createTransactionId(this.getRuleId());
		for (StockInfo info : resultSet) {
			logger.info(info.toString());
			query.addRuleResult(historyId, info.getCode());
//			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://finance.sina.com.cn/realstock/company/" + info.getCode() + "/nc.shtml");
		}
		query.addRuleHistory(this.getRuleId(), historyId, StockConstants.RESULT_STATUS_SUCCESS, 0l, resultSet.size());
	}

	private void executeRule(DBQuery query, List<StockInfo> resultSet)
			throws Exception {
		List<Stock> stocks = query.getStockList();
		if (stocks != null && stocks.size() > 0) {
			for (Stock stock : stocks) {
				logger.debug("Stock name: " + stock.getName() + " and code: " + stock.getCode());
				HTTPQuery httpQuery = getHTTPQuery();
				try {
					StockInfo info = new StockInfo(stock);
					httpQuery.richStockInfo(info);
					logger.debug(info.toString());
					Rule rule = getRule();
					if (rule.isSatisfy(info)) {
						resultSet.add(info);
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
	
	protected abstract HTTPQuery getHTTPQuery();
	
	protected abstract Rule getRule();
	
	protected abstract String getRuleId();

}
