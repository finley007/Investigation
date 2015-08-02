package stock.json;

import org.junit.Test;

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
