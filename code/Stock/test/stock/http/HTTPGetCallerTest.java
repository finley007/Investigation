package stock.http;

import org.junit.Before;
import org.junit.Test;

public class HTTPGetCallerTest {
	
	private HTTPCaller caller = HTTPCaller.getIns(HTTPCaller.Method.Get);

	@Before
	public void setUp() throws Exception {
		caller.setUrl("http://qt.gtimg.cn/q=ff_sh600278");
	}

	@Test
	public void testCallHTTPServ() {
		try {
			String result = caller.callHTTPServ(null);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
