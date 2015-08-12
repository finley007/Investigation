package stock.analysis.impl;

import java.text.MessageFormat;
import java.util.List;

import stock.analysis.StockAnalysis;
import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.util.BrowserOpener;
import stock.vo.Stock;

public class ShowDiagram implements StockAnalysis {

	@Override
	public void doAnalysis(String historyId, int init, int size) throws Exception {
		// TODO Auto-generated method stub
		DBConnector connector = new MysqlConnector();
		DBQuery query = new DBQueryImpl();
		query.setConn(connector);
		List<Stock> list = query.getRuleResultByHistoryId(historyId);
		for (int i = init; i < init + size; i++) {
			BrowserOpener.open(getURL(list.get(i)));
		}
	}
	
	private String getURL(Stock stock) {
		return MessageFormat.format("http://q.stock.sohu.com/cn/{0}/index.shtml", new Object[]{stock.getCode().substring(2)});
	}

}
