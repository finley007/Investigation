package stock.db;

import java.util.List;
import java.util.Map;

import stock.db.connect.DBConnector;
import stock.vo.AlertVO;
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

	public void addAlert(String stockCode, Integer alertType, String message) throws Exception;
	
	public List<AlertVO> readAlert() throws Exception;
	
	public void updateAlertStatus(String alertId, Integer status) throws Exception;
	
	public String addRuleHistory(String ruleId, String historyId, Integer result, Long timeCost, Integer stockNum) throws Exception;
	
	public void addRuleResult(String historyId, String stockCode) throws Exception;
	
	public List<Stock> getRuleResultByHistoryId(String historyId) throws Exception;
}
