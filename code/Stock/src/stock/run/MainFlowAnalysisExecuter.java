package stock.run;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import stock.db.DBQuery;
import stock.db.DBQueryImpl;
import stock.db.connect.DBConnector;
import stock.db.connect.MysqlConnector;
import stock.http.HTTPCaller;
import stock.http.HTTPQuery;
import stock.http.impl.TencentHTTPQuery;
import stock.rule.MainInputFlowRule;
import stock.rule.Rule;
import stock.vo.Stock;
import stock.vo.StockInfo;

/**
 * @author liuli
 *
 * This executer is used for list all the stocks that have big main inflow
 */
public class MainFlowAnalysisExecuter {
	
	private static String URL_TEMPLATE = "http://qt.gtimg.cn/q=ff_{0}";
	
	public static void main(String[] args) {
		DBConnector connector = new MysqlConnector();
		DBQuery query = new DBQueryImpl();
		query.setConn(connector);
		List<StockInfo> resultSet = new ArrayList<StockInfo>();
		try {
			List<Stock> stocks = query.getStockList();
			if (stocks != null && stocks.size() > 0) {
				for (Stock stock : stocks) {
					System.out.println("Stock name: " + stock.getName() + " and code: " + stock.getCode());
					HTTPCaller caller = HTTPCaller.getIns(HTTPCaller.Method.Get);
					caller.setUrl(createURL(stock));
					HTTPQuery httpQuery = new TencentHTTPQuery();
					httpQuery.setHTTPCaller(caller);
					try {
						StockInfo info = httpQuery.getStockInfo(stock);
						System.out.println(info.toString());
						Rule rule = new MainInputFlowRule();
						if (rule.isSatisfy(info)) {
							resultSet.add(info);
						}
					} catch (Exception e) {
						System.out.println("Ths stock: " + stock.getCode() + " are failed to obtain");
					}
				}
			}
			System.out.println("*******************************************");
			System.out.println("The stocks to be checked: ");
			for (StockInfo info : resultSet) {
				System.out.println(info.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String createURL(Stock stock) {
		return MessageFormat.format(URL_TEMPLATE, new Object[]{stock.getCode()});
	}

}
