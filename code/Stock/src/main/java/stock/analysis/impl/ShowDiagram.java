package stock.analysis.impl;

import java.util.List;

import stock.analysis.StockAnalysis;
import stock.manager.StockManager;
import stock.manager.impl.StockManagerImpl;
import stock.model.Stock;
import stock.util.BrowserOpener;
import stock.util.StockUtils;

public class ShowDiagram implements StockAnalysis {

	@Override
	public void doAnalysis(String historyId, int init, int size) throws Exception {
		// TODO Auto-generated method stub
		StockManager query = new StockManagerImpl();
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
