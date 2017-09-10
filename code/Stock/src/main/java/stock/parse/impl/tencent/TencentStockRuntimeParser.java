package stock.parse.impl.tencent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.model.Stock;
import stock.parse.InfoParser;


public class TencentStockRuntimeParser implements InfoParser {
	
	Logger logger = LoggerFactory.getLogger(TencentStockRuntimeParser.class);

	public void parseStockInfo(Stock stock, String info) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("Parse stock runtime info for " + stock.getLabel() + " and info content: " + info);
		String[] params = info.split("~");
		Double currentPrice = Double.valueOf(params[3]);
		stock.setCurrentPrice(currentPrice);
		if (params[39] != null && !"".equals(params[39])) {
			stock.setPer(Double.valueOf(params[39]));
		}
	}
	
}
