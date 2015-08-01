package stock.cache;

import org.junit.Test;

public class StockCacheTest {

	@Test
	public void testGetNameByCode() {
		try {
			System.out.println(StockCache.getNameByCode("sh600000"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
