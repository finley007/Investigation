package stock.analysis;

import stock.vo.Stock;
import stock.vo.StockInfo;

public class TencentStockInfoParser implements InfoParser {

	public StockInfo parseStockInfo(Stock stock, String info) throws Exception {
		// TODO Auto-generated method stub
		StockInfo stockInfo = new StockInfo(stock);
		System.out.println(info);
		parseStockInfo(stockInfo, info);
		return stockInfo;
	}
	
	private void parseStockInfo(StockInfo stockInfo, String info) {
		String[] params = info.split("~");
		stockInfo.setMainInflow(Double.valueOf(params[1]));
		stockInfo.setMainOutflow(Double.valueOf(params[2]));
		stockInfo.setRetailInflow(Double.valueOf(params[5]));
		stockInfo.setRetailOutflow(Double.valueOf(params[6]));
	}

}
