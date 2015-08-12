package stock.cache;

import java.util.HashMap;
import java.util.Map;

import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;

/**
 * This class is used for dictionary cache
 * 
 * @author liuli
 *
 */
public class StockCache {
	
	private StockCache () throws Exception {
		init();
	}
	
	private Map<String, String> stock = new HashMap<String, String>(); 
	
	private static StockCache ins;
	
	private void init() throws Exception {
		DBConnector connector = new MysqlConnector();
		DBQuery query = new DBQueryImpl();
		query.setConn(connector);
		stock = query.getStockCodeNamePair();
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
//		String name = getIns().getStock().get(code);
//		if (name == null) {
//			name = "";
//		}
//		return name;
		return code;
	}

}
