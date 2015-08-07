package stock.db;

import java.util.List;
import java.util.Map;

import stock.db.connect.DBConnector;
import stock.vo.MyStockInfo;
import stock.vo.Stock;

/**
 * @author liuli
 * 
 * This interface is used for DB query
 */

public interface DBQuery {
	
	public DBConnector getConn();
	
	public void setConn(DBConnector connector);
	
	public List<Stock> getStockList() throws Exception;
	
	public List<MyStockInfo> getMyStock() throws Exception;
	
	public MyStockInfo getMyStockByTransId(String transId) throws Exception;
	
	public MyStockInfo getMyStockByCode(String code) throws Exception;
	
	public void addMyStock(MyStockInfo info) throws Exception;
	
	public void updateMyStock(MyStockInfo info, Integer action) throws Exception;
	
	public Map<String, String> getStockCodeNamePair() throws Exception;
	
	public void updateMonitorStatus(String transId, Integer isMonitor) throws Exception;

}
