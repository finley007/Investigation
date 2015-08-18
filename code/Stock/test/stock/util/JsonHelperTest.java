package stock.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import stock.vo.MyStockInfo;

public class JsonHelperTest {

	@Test
	public void testToJson() {
		MyStockInfo info = new MyStockInfo();
		info.setCode("code");
		info.setTransId("transId");
		System.out.println(JsonHelper.toJson(info));
		
		Map map = new HashMap();
		map.put("totalPrice", 0.0);
		map.put("fee", 0.0);
		System.out.println(JsonHelper.toJson(map));
	}

}
