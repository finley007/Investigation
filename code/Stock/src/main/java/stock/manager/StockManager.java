package stock.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import stock.model.MyAction;
import stock.model.MyStock;
import stock.model.RuleItem;
import stock.model.RuleExecuteHistory;
import stock.model.Stock;
import stock.vo.AlertVO;
import stock.vo.RuleItemVO;
import stock.vo.TransInfoVO;

/**
 * @author liuli
 * 
 * This interface is used for business logic
 */

public interface StockManager {
	
	public List<Stock> getStockList() throws Exception;

	public int getStockCount() throws Exception;

	public List<Stock> getStockListByCondition(String condition) throws Exception;
	
	public List<MyStock> getMyCurrentStock() throws Exception;
	
	public MyStock getMyCurrentStockByCode(String code) throws Exception;

	public MyStock getMyStockByTransId(String transId) throws Exception;
	
	public List<MyStock> getMyEverStockByCode(String code) throws Exception;
	
	public void addMyStock(MyStock info) throws Exception;
	
	public void updateMyStock(MyStock info, Integer action) throws Exception;
	
	public void updateMonitorStatus(String transId, Integer isMonitor) throws Exception;

	public void addAlert(String stockCode, Integer alertType, String message) throws Exception;
	
	public List<AlertVO> readAlert() throws Exception;
	
	public void updateAlertStatus(String alertId, Integer status) throws Exception;
	
	public String addRuleExecuteHistory(String ruleId, String historyId, Integer result, Long timeCost, Integer stockNum) throws Exception;
	
	public void addRuleExecuteResult(String historyId, String stockCode) throws Exception;
	
	public List<Stock> getRuleResultByHistoryId(String historyId) throws Exception;
	
	public void updateRuleResultTrend(String historyId, String stockCode, Integer day, Double profit) throws Exception;

	public List<RuleItem> getRuleItemByType(Integer type) throws Exception;
	
	public void saveOrUpdateRuleItem(RuleItem ruleItem) throws Exception;
	
	public void deleteRuleItem(RuleItem ruleItem) throws Exception;
	
	public RuleItem getRuleItemByPK(RuleItem ruleItem) throws Exception;
	
	public Map<String, Integer> initDateStatus() throws Exception;
	
	public List<RuleExecuteHistory> getRuleExecuteHistoryByRuleId(String ruleId) throws Exception;
	
	public List<Stock> getStockCategory() throws Exception;
	
	public List<MyAction> getActionByTransId(String transId) throws Exception;
	
	public TransInfoVO queryTransInfo(String stockCode) throws Exception;
}
