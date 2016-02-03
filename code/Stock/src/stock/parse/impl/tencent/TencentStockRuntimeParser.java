package stock.parse.impl.tencent;

import org.apache.log4j.Logger;

import stock.model.Stock;
import stock.parse.InfoParser;

import com.mysql.jdbc.StringUtils;

public class TencentStockRuntimeParser implements InfoParser {
	
	Logger logger = Logger.getLogger(TencentStockRuntimeParser.class);

	public void parseStockInfo(Stock stock, String info) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(info);
		String[] params = info.split("~");
		Double currentPrice = Double.valueOf(params[3]);
		stock.setCurrentPrice(currentPrice);
		if (!StringUtils.isNullOrEmpty(params[39])) {
			stock.setPer(Double.valueOf(params[39]));
		}
	}
	
}
