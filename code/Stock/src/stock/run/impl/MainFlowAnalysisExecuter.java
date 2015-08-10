package stock.run.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import stock.db.DBQuery;
import stock.db.DBQueryImpl;
import stock.db.connect.DBConnector;
import stock.db.connect.MysqlConnector;
import stock.http.HTTPCaller;
import stock.http.HTTPQuery;
import stock.http.impl.tencent.TencentHTTPFlowQuery;
import stock.rule.Rule;
import stock.rule.impl.MainInputFlowRule;
import stock.run.BaseExcecuter;
import stock.vo.Stock;
import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * This executer is used for list all the stocks that have big main inflow
 */
public class MainFlowAnalysisExecuter extends BaseExcecuter{
	
	private static Logger logger = LogManager.getLogger(MainFlowAnalysisExecuter.class);  
	
	public void excecute() {
		DBConnector connector = new MysqlConnector();
		DBQuery query = new DBQueryImpl();
		query.setConn(connector);
		List<StockInfo> resultSet = new ArrayList<StockInfo>();
		try {
			List<Stock> stocks = query.getStockList();
			if (stocks != null && stocks.size() > 0) {
				for (Stock stock : stocks) {
					logger.debug("Stock name: " + stock.getName() + " and code: " + stock.getCode());
					HTTPQuery httpQuery = new TencentHTTPFlowQuery();
					try {
						StockInfo info = new StockInfo(stock);
						httpQuery.richStockInfo(info);
						logger.debug(info.toString());
						Rule rule = new MainInputFlowRule();
						if (rule.isSatisfy(info)) {
							resultSet.add(info);
						}
					} catch (Exception e) {
						logger.error("Ths stock: " + stock.getCode() + " are failed to obtain");
					}
				}
			}
			logger.info("*******************************************");
			logger.info("The stocks to be checked: ");
			for (StockInfo info : resultSet) {
				logger.info(info.toString());
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://finance.sina.com.cn/realstock/company/" + info.getCode() + "/nc.shtml");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}