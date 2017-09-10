package stock.feed.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import stock.feed.InfoFeeder;
import stock.feed.http.tencent.TencentHTTPFlowFeeder;
import stock.model.Stock;

/**
 * TencentHTTPFlowFeeder Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 15, 2017</pre>
 */
public class TencentHTTPFlowFeederTest {

    private InfoFeeder feeder = new  TencentHTTPFlowFeeder();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getURL(String stockCode)
     */
    @Test
    public void testGetURL() throws Exception {
//TODO: Test goes here...
        Stock stock = new Stock();
        stock.setCode("603887");
        feeder.feedInfo(stock);
        System.out.println(stock);
    }


} 
