package stock.db;

import java.util.Date;
import java.util.List;
import java.util.Map;

import stock.db.connect.DBConnector;
import stock.vo.AlertVO;
import stock.vo.MyStockInfo;
import stock.vo.RuleItemVO;
import stock.vo.RuleRunHistoryVO;
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
	
	public void updateRuleResultTrend(String historyId, String stockCode, Integer day, Double profit) throws Exception;

	public List<RuleItemVO> getRuleItemByType(Integer type) throws Exception;
	
	public void saveOrUpdateRuleItem(RuleItemVO ruleItem) throws Exception;
	
	public void deleteRuleItem(RuleItemVO ruleItem) throws Exception;
	
	public RuleItemVO getRuleItemById(String ruleId) throws Exception;
	
	public void clearCalendar(Date start, Date end) throws Exception;
	
	public void insertCalendar(Date date, Integer status) throws Exception;
	
	public Map<String, Integer> initDateStatus() throws Exception;
	
	public List<RuleRunHistoryVO> getRuleRunHistoryByRuleId(String ruleId) throws Exception;
	
	public List<Stock> getRuleResultByRuleHisId(String ruleId) throws Exception;
}
