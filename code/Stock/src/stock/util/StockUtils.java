package stock.util;

import java.text.MessageFormat;

public class StockUtils {
	
	private static final Double STAMP_TAX_RATE = 0.001;
	
	private static final Double TRANSFER_FEE_RATE = 0.0006;
	
	private static final Double COMMISSION_RATE = 0.0003;
	
	private static final Double COMMISSION_MIN = 5.0;
	
	public static Double caculateFee(Integer action, Integer quantity, Double price) {
		Double fee = 0.0;
		if (StockConstants.ACTION_TYPE_SELL == action) {
			fee += quantity * price * STAMP_TAX_RATE;
		}
		fee += quantity * TRANSFER_FEE_RATE;
		if (COMMISSION_RATE * quantity * price > COMMISSION_MIN) {
			fee += COMMISSION_RATE * quantity * price;
		} else {
			fee += COMMISSION_MIN;
		}
		return fee;
	}
	
	public static String getURL(String stockCode) {
		return MessageFormat.format("http://q.stock.sohu.com/cn/{0}/index.shtml", new Object[]{stockCode});
	}
}
