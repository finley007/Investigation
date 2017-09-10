package stock.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import stock.feed.InfoFeeder;
import stock.feed.http.HTTPFeeder;
import stock.feed.http.MyStockInfoFeeder;

/**
 * Stock Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 6, 2017</pre>
 */
public class StockTest {

    private Stock stock  = new Stock();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getCode()
     */
    @Test
    public void testGetCode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setCode(String code)
     */
    @Test
    public void testSetCode() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getName()
     */
    @Test
    public void testGetName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setName(String name)
     */
    @Test
    public void testSetName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getLabel()
     */
    @Test
    public void testGetLabel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setLabel(String label)
     */
    @Test
    public void testSetLabel() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getPer()
     */
    @Test
    public void testGetPer() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setPer(Double per)
     */
    @Test
    public void testSetPer() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getCurrentPrice()
     */
    @Test
    public void testGetCurrentPrice() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setCurrentPrice(Double currentPrice)
     */
    @Test
    public void testSetCurrentPrice() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getMainInflow()
     */
    @Test
    public void testGetMainInflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setMainInflow(Double mainInflow)
     */
    @Test
    public void testSetMainInflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getMainOutflow()
     */
    @Test
    public void testGetMainOutflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setMainOutflow(Double mainOutflow)
     */
    @Test
    public void testSetMainOutflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRetailInflow()
     */
    @Test
    public void testGetRetailInflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setRetailInflow(Double retailInflow)
     */
    @Test
    public void testSetRetailInflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRetailOutflow()
     */
    @Test
    public void testGetRetailOutflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setRetailOutflow(Double retailOutflow)
     */
    @Test
    public void testSetRetailOutflow() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: clearDailyPrice()
     */
    @Test
    public void testClearDailyPrice() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addDailyPrice(String date, DailyPriceVO vo)
     */
    @Test
    public void testAddDailyPrice() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getDailyPrice()
     */
    @Test
    public void testGetDailyPrice() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: toJson()
     */
    @Test
    public void testToJson() throws Exception {
//TODO: Test goes here...
        Stock stock = new Stock("601212");
        InfoFeeder feeder = new MyStockInfoFeeder();
        feeder.feedInfo(stock);
        System.out.println(stock.toJson());
    }


    @Test
    public void testAddPrefix() throws Exception {
        String code = stock.addPrefix("600051");
        Assert.assertEquals("sh600051", code);
        code = stock.addPrefix("000001");
        Assert.assertEquals("sz000001", code);
        code = stock.addPrefix("300001");
        Assert.assertEquals("sz300001", code);
    }


} 
