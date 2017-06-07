package stock.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import stock.model.MyStock;

public class JsonHelperTest {

	@Test
	public void testToJson() {
		MyStock info = new MyStock();
//		info.setCode("code");
//		info.setTransId("transId");
		System.out.println(JsonHelper.toJson(info));
		
		Map map = new HashMap();
		map.put("totalPrice", 0.0);
		map.put("fee", 0.0);
		System.out.println(JsonHelper.toJson(map));
	}

}
