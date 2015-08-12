package stock.util;

import org.junit.Test;

import stock.util.JsonHelper;
import stock.vo.MyStockInfo;

public class JsonHelperTest {

	@Test
	public void testToJson() {
		MyStockInfo info = new MyStockInfo();
		info.setCode("code");
		info.setTransId("transId");
		System.out.println(JsonHelper.toJson(info));
	}

}
