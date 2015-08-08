package stock.analysis;

import org.apache.log4j.Logger;

import stock.vo.StockInfo;

public class TencentStockFlowParser implements InfoParser {
	
	Logger logger = Logger.getLogger(TencentStockFlowParser.class);

	public void parseStockInfo(StockInfo stock, String info) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(info);
		String[] params = info.split("~");
		stock.setMainInflow(Double.valueOf(params[1]));
		stock.setMainOutflow(Double.valueOf(params[2]));
		stock.setRetailInflow(Double.valueOf(params[5]));
		stock.setRetailOutflow(Double.valueOf(params[6]));
	}
	
}