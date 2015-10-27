package stock.analysis.impl;

import java.util.List;

import stock.analysis.StockAnalysis;
import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.util.BrowserOpener;
import stock.util.StockUtils;
import stock.vo.Stock;

public class ShowDiagram implements StockAnalysis {

	@Override
	public void doAnalysis(String historyId, int init, int size) throws Exception {
		// TODO Auto-generated method stub
		DBConnector connector = new MysqlConnector();
		DBQuery query = new DBQueryImpl();
		query.setConn(connector);
		List<Stock> list = query.getRuleResultByHistoryId(historyId);
		if (size <= 0) {
			size = list.size();
			init = 0;
		}
		for (int i = init; i < init + size; i++) {
			BrowserOpener.open(StockUtils.getURL(list.get(i).getCode().substring(2)));
		}
	}
	
}
