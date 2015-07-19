package stock.run;

import java.text.MessageFormat;
import java.util.List;

import stock.db.DBQuery;
import stock.db.DBQueryImpl;
import stock.db.connect.DBConnector;
import stock.db.connect.MysqlConnector;
import stock.http.HTTPCaller;
import stock.http.HTTPQuery;
import stock.http.impl.TencentHTTPQuery;
import stock.vo.Stock;

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
		try {
			List<Stock> stocks = query.getStockList();
			if (stocks != null && stocks.size() > 0) {
				for (Stock stock : stocks) {
					System.out.println("Stock name: " + stock.getName() + " and code: " + stock.getCode());
					HTTPCaller caller = HTTPCaller.getIns(HTTPCaller.Method.Get);
					caller.setUrl(createURL(stock));
					HTTPQuery httpQuery = new TencentHTTPQuery();
					httpQuery.setHTTPCaller(caller);
					System.out.println(httpQuery.getStockInfo(stock).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String createURL(Stock stock) {
		return MessageFormat.format(URL_TEMPLATE, new Object[]{stock.getCode()});
	}

}
