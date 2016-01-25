package stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stock.model.MyAction;
import stock.model.MyStock;
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
    
    
}