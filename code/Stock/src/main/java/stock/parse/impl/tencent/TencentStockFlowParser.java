package stock.parse.impl.tencent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.model.Stock;
import stock.parse.InfoParser;

public class TencentStockFlowParser implements InfoParser {
	
	Logger logger = LoggerFactory.getLogger(TencentStockFlowParser.class);

	public void parseStockInfo(Stock stock, String info) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("Parse stock flow info for " + stock.getLabel() + " and info content: " + info);
		String[] params = info.split("~");
		if (params != null && params.length > 0) {
			stock.setMainInflow(Double.valueOf(params[1]));
			stock.setMainOutflow(Double.valueOf(params[2]));
			stock.setRetailInflow(Double.valueOf(params[5]));
			stock.setRetailOutflow(Double.valueOf(params[6]));
		}
	}
	
}
