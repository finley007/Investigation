package stock.run;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import stock.feed.http.MyStockInfoFeeder;
import stock.model.Stock;
import stock.rule.Rule;
import stock.rule.impl.FallTrendRule;
import stock.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/** 
* RuleExecuter Tester. 
* 
* @author <Authors name> 
* @since <pre>Jun 11, 2017</pre> 
* @version 1.0 
*/ 
public class RuleExecuterTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: run() 
* 
*/ 
@Test
public void testRun() throws Exception { 
//TODO: Test goes here...
    try {
        Rule rule = new FallTrendRule();
        List<Stock> stockList = new ArrayList<Stock>();
        stockList.add(new Stock("603977"));
        RuleExecuter executer = new RuleExecuter();
        executer.setFeeder(new MyStockInfoFeeder());
        executer.setRule(rule);
        executer.setStockList(stockList);
        executer.run();
    } catch (Exception e) {
        e.printStackTrace();
    }
} 

/** 
* 
* Method: clearStockList() 
* 
*/ 
@Test
public void testClearStockList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setStockList(List<Stock> list) 
* 
*/ 
@Test
public void testSetStockList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addStockList(Stock stock) 
* 
*/ 
@Test
public void testAddStockList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getHistoryId() 
* 
*/ 
@Test
public void testGetHistoryId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setHistoryId(String historyId) 
* 
*/ 
@Test
public void testSetHistoryId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setRule(Rule rule) 
* 
*/ 
@Test
public void testSetRule() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getFeeder() 
* 
*/ 
@Test
public void testGetFeeder() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setFeeder(InfoFeeder feeder) 
* 
*/ 
@Test
public void testSetFeeder() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: init() 
* 
*/ 
@Test
public void testInit() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = RuleExecuter.getClass().getMethod("init"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: excecute() 
* 
*/ 
@Test
public void testExcecute() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = RuleExecuter.getClass().getMethod("excecute"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: solveResult(List<Stock> resultSet) 
* 
*/ 
@Test
public void testSolveResult() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = RuleExecuter.getClass().getMethod("solveResult", List<Stock>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: executeRule() 
* 
*/ 
@Test
public void testExecuteRule() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = RuleExecuter.getClass().getMethod("executeRule"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
