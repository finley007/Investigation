package stock.alert.action.impl;

import stock.alert.action.AlertAction;
import stock.util.BrowserOpener;
import stock.util.StockUtils;

public class ExplorerAction implements AlertAction {

	@Override
	public void doAction(String stockCode, String message) throws Exception {
		// TODO Auto-generated method stub
		BrowserOpener.open(StockUtils.getURL(stockCode));
	}

}
