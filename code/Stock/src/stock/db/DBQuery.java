package stock.db;

import java.util.List;

import stock.db.connect.DBConnector;
import stock.vo.Stock;
import stock.vo.StockInfo;

/**
 * @author liuli
 * 
 * This interface is used for DB query
 */

public interface DBQuery {
	
	public DBConnector getConn();
	
	public void setConn(DBConnector connector);
	
	public List<Stock> getStockList() throws Exception;
	
	public List<StockInfo> getMyStock() throws Exception;

}
