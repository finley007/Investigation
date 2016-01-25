package stock.parse.impl.tencent;

import org.apache.log4j.Logger;

import stock.model.MyStock;
import stock.parse.InfoParser;

public class TencentStockFlowParser implements InfoParser {
	
	Logger logger = Logger.getLogger(TencentStockFlowParser.class);

	public void parseStockInfo(MyStock myStock, String info) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(info);
		String[] params = info.split("~");
		myStock.getStock().setMainInflow(Double.valueOf(params[1]));
		myStock.getStock().setMainOutflow(Double.valueOf(params[2]));
		myStock.getStock().setRetailInflow(Double.valueOf(params[5]));
		myStock.getStock().setRetailOutflow(Double.valueOf(params[6]));
	}
	
}
