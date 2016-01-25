package stock.parse.impl.tencent;

import org.apache.log4j.Logger;

import stock.model.MyStock;
import stock.parse.InfoParser;
import stock.util.CommonUtils;
import stock.util.StockConstants;

import com.mysql.jdbc.StringUtils;

public class TencentStockRuntimeParser implements InfoParser {
	
	Logger logger = Logger.getLogger(TencentStockRuntimeParser.class);

	public void parseStockInfo(MyStock myStock, String info) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(info);
		String[] params = info.split("~");
		Double currentPrice = Double.valueOf(params[3]);
		myStock.getStock().setCurrentPrice(currentPrice);
		Double profit = CommonUtils.round((currentPrice - myStock.getBuyPrice()) * myStock.getQuantity(), StockConstants.PRICE_SCALE);
		myStock.setProfit(profit);
		Double profitRate = CommonUtils.round((currentPrice - myStock.getBuyPrice()) * 100 / myStock.getBuyPrice(), StockConstants.PRICE_SCALE);
		myStock.setProfitRate(profitRate);
		if (!StringUtils.isNullOrEmpty(params[39])) {
			myStock.getStock().setPer(Double.valueOf(params[39]));
		}
	}
	
}
