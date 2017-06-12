package stock.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import stock.model.MyAction;
import stock.model.MyStock;
import stock.model.RuleItem;
import stock.model.RuleExecuteHistory;
import stock.model.Stock;

public class IStockOperationTest {
	
	private ApplicationContext ctx;
	private IStockOperation mapper;
	
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		mapper = (IStockOperation)ctx.getBean("stockMapper"); 
	}
	
	@Test
	public void testSelectMyStockByPK() throws IOException {
		MyStock myStock = mapper.selectMyStockByPK("20150805110813231sh600081");
		System.out.println(myStock.getTransactionId());
		System.out.println(myStock.getBuyPrice());
		System.out.println(myStock.getCloseTime());
		System.out.println(myStock.getIsMonitor());
		System.out.println(myStock.getOpenTime());
		System.out.println(myStock.getProfit());
		System.out.println(myStock.getProfitRate());
		System.out.println(myStock.getQuantity());
		System.out.println(myStock.getStatus());
		System.out.println(myStock.getStock().getCode());
		System.out.println(myStock.getStock().getName());
		System.out.println(myStock.getStock().getLabel());
	}
	
	@Test
	public void testSelectMyCurrentStockByCode() throws IOException {
		MyStock myStock = mapper.selectMyCurrentStockByCode("sh600051");
		System.out.println(myStock.getTransactionId());
		System.out.println(myStock.getBuyPrice());
		System.out.println(myStock.getCloseTime());
		System.out.println(myStock.getIsMonitor());
		System.out.println(myStock.getOpenTime());
		System.out.println(myStock.getProfit());
		System.out.println(myStock.getProfitRate());
		System.out.println(myStock.getQuantity());
		System.out.println(myStock.getStatus());
		System.out.println(myStock.getStock().getCode());
		System.out.println(myStock.getStock().getName());
		System.out.println(myStock.getStock().getLabel());
	}
	
	@Test
	public void testSelectMyCurrentStock() {
		List<MyStock> list = mapper.selectMyCurrentStock();
		assertEquals(5, list.size());
	}
	
	@Test
	public void testSelectMyEverStockByCode() {
		List<MyStock> list = mapper.selectMyEverStockByCode("sh600500");
		assertEquals(7, list.size());
		list = mapper.selectMyEverStockByCode(null);
		assertEquals(48, list.size());
	}
	
	@Test
	public void testInsertMyStock() {
		IStockOperation mapper = (IStockOperation)ctx.getBean("stockMapper"); 
		MyStock myStock = new MyStock();
		myStock.setTransactionId("aaaaaa");
		myStock.setBuyPrice(2.0);
		myStock.setCloseTime(new Date());
		myStock.setIsMonitor(1);
		myStock.setOpenTime(new Date());
		myStock.setProfit(1000.0);
		myStock.setProfitRate(0.5);
		myStock.setQuantity(200);
		myStock.setStatus(1);
		Stock stock = new Stock();
		stock.setCode("sh600051");
		myStock.setStock(stock);
		mapper.insertMyStock(myStock);
	}

	@Test
	public void testInsertSelective() {
	}
	
	@Test
	public void testUpdateByPrimaryKeySelective() {
		MyStock myStock = mapper.selectMyStockByPK("aaaaaa");
		Stock stock = new Stock();
		stock.setCode("sh600081");
		myStock.setStock(stock);
		mapper.updateMyStockByPKSelective(myStock);
		myStock = mapper.selectMyStockByPK("aaaaaa");
		assertEquals("sh600081", "sh600081");
	}

	@Test
	public void testUpdateByPrimaryKey() {
	}
	
	@Test
	public void testDeleteByPrimaryKey() {
		mapper.deleteMyStockByPK("aaaaaa");
	}
	
	@Test
	public void testSelectAllStock() {
		List<Stock> list = mapper.selectAllStock();
		for (Stock stock : list) {
			System.out.println(stock.getCode());
		}
	}
	
	@Test
	public void testSelectActionByTransId() {
		List<MyAction> list = mapper.selectActionByTransId("20150805110855507sh600500");
		for (MyAction action : list) {
			System.out.println(action.getActionId());
		}
	}
	
	@Test
	public void testInsertMyAction() {
		MyAction action = new MyAction();
		action.setActionId("aa");
		action.setActionType(1);
		action.setPrize(12.0);
		action.setQuantity(200);
		action.setTime(new Date());
		action.setTransactionId("aa");
		mapper.insertMyAction(action);
	}
	
	@Test
	public void testSelectRuleItemByType() {
		List<RuleItem> ruleItemList = mapper.selectRuleItemByType(3);
		assertEquals(4, ruleItemList.size());
	}
	
	@Test
	public void testSelectRuleRunHistoryByRuleId() {
		List<RuleExecuteHistory> ruleRunHistoryList = mapper.selectRuleExecuteHistoryByRuleId("RULE_HISTORY_1");
		assertEquals(25, ruleRunHistoryList.size());
	}

	@Test
	public void testSelectRuleResultByHistoryId() {
		List<Stock> stockList = mapper.selectRuleExecuteResultByHistoryId("20150908110903337RULE_HISTORY_1");
		System.out.println(stockList.get(0).getCode());
	}

	@Test
	public void testSelectStockByCondition() {
		List<Stock> stockList = mapper.selectStockByCondition("where code like '%0'");
		System.out.println(stockList.size());
	}
}
