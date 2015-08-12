package stock.parse.impl.tencent;

import org.apache.log4j.Logger;

import stock.parse.InfoParser;
import stock.vo.StockInfo;

public class TencentStockRuntimeParser implements InfoParser {
	
	Logger logger = Logger.getLogger(TencentStockRuntimeParser.class);

	public void parseStockInfo(StockInfo stock, String info) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(info);
		String[] params = info.split("~");
		stock.setCurrentPrice(Double.valueOf(params[3]));
	}
	
}
