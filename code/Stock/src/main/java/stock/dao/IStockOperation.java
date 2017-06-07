package stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stock.model.Calendar;
import stock.model.DateInterval;
import stock.model.MyAction;
import stock.model.MyStock;
import stock.model.RuleItem;
import stock.model.RuleResult;
import stock.model.RuleRunHistory;
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
     * rule_run_history operation
     */
    List<RuleRunHistory> selectRuleRunHistoryByRuleId(@Param("ruleId")String ruleId);
    
    void insertRuleRunHistory(RuleRunHistory history);
    
    /**
     * rule_result operation
     */
    List<Stock> selectRuleResultByHistoryId(@Param("historyId")String historyId);
    
    void insertRuleResult(RuleResult ruleResult);
    
    /**
     * calendar operation
     */
    List<Calendar> selectAllCalendar();
    
    void deleteCalendarBetweenInterval(DateInterval interval);
    
    void insertCalendar(Calendar cal);
    
    
}