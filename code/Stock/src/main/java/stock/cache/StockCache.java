package stock.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stock.context.StockAppContext;
import stock.dao.IStockOperation;
import stock.manager.StockManager;
import stock.model.Stock;

/**
 * This class is used for dictionary cache
 * 
 * @author liuli
 *
 */
public class StockCache {
	
	private IStockOperation stockOperation = (IStockOperation)StockAppContext.getBean("stockMapper");
	
	private StockCache () throws Exception {
		init();
	}
	
	private Map<String, String> stock = new HashMap<String, String>(); 
	
	private static StockCache ins;
	
	private void init() throws Exception {
		stock = getStockCodeNamePair();
	}
	
	public Map<String, String> getStock() {
		return stock;
	}

	public void setStock(Map stock) {
		this.stock = stock;
	}

	private static StockCache getIns() throws Exception {
		if (ins == null) {
			ins = new StockCache();
		}
		return ins;
	}
	
	public static String getNameByCode(String code) throws Exception {
		String name = getIns().getStock().get(code);
		if (name == null) {
			name = "";
		}
		return name;
	}
	
	private Map<String, String> getStockCodeNamePair() throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		List<Stock> allStocks = stockOperation.selectAllStock();
		for (Stock stock : allStocks) {
			String code = stock.getCode();
			String name = stock.getName();
			result.put(code, name);
		}
		return result;
	}

}
