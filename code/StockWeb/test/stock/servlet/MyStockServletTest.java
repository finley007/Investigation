package stock.servlet;

import org.junit.Test;

public class MyStockServletTest {
	

	@Test
	public void testCreateResponse() {
		MyStockServlet servlet = new MyStockServlet();
		System.out.println(servlet.createResponse(null));
	}

}
