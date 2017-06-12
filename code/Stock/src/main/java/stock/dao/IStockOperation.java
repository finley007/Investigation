package stock.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import stock.model.Calendar;
import stock.model.DateInterval;
import stock.model.MyAction;
import stock.model.MyStock;
import stock.model.RuleItem;
import stock.model.RuleExecuteResult;
import stock.model.RuleExecuteHistory;
import stock.model.Stock;

public interface IStockOperation {

	/**
	 * my_stock operation
	 */
	MyStock selectMyStockByPK(String transactionId);
	
	List<MyStock> selectMyCurrentStock();
	
	MyStock selectMyCurrentStockByCode(@Param("stockCode")String stockCode);
    
    List<MyStock> selectMyEverStockByCode(@Param("stockCode")String stockCode);
    
    int insertMyStock(MyStock record);

    int insertSelectiveMyStock(MyStock record);

    int updateMyStockByPKSelective(MyStock record);

    int updateMyStockByPK(MyStock record);
    
    int deleteMyStockByPK(String transactionId);
    
    
    /**
     * all_stock operation
     */
    List<Stock> selectAllStock();

    List<Stock> selectStockByCondition(@Param("condition")String condition);

    List<Stock> selectAllMyStock();
    
    int insertStock(Stock record);

    int insertSelectiveStock(Stock record);
    
    
    /**
     * my_action operation
     */
    List<MyAction> selectActionByTransId(@Param("transId")String transId);
    
    int insertMyAction(MyAction record);
    
    int insertSelectiveMyAction(MyAction record);
    
    /**
     * rule_item operation
     */
    RuleItem selectRuleItemByPK(RuleItem ruleItem);
    
    List<RuleItem> selectRuleItemByType(@Param("ruleType")int ruleType);
    
    void insertRuleItem(RuleItem ruleItem);
    
    void insertSelectiveRuleItem(RuleItem ruleItem);
    
    void updateRuleItemByPKSelective(RuleItem ruleItem);
    
    void updateRuleItemByPK(RuleItem ruleItem);
    
    void deleteRuleItemByPK(RuleItem ruleItem);
    
    /**
     * rule_execute_history operation
     */
    List<RuleExecuteHistory> selectRuleExecuteHistoryByRuleId(@Param("ruleId")String ruleId);
    
    void insertRuleExecuteHistory(RuleExecuteHistory history);
    
    /**
     * rule_execute_result operation
     */
    List<Stock> selectRuleExecuteResultByHistoryId(@Param("historyId")String historyId);
    
    void insertRuleExecuteResult(RuleExecuteResult ruleExecuteResult);
    
    /**
     * calendar operation
     */
    List<Calendar> selectAllCalendar();
    
    void deleteCalendarBetweenInterval(DateInterval interval);
    
    void insertCalendar(Calendar cal);
    
    
}