package stock.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StockUtils {
	
	/**
	 * This method is used for create transaction id:
	 * yyyymmddhhmissstockcode
	 * 
	 * @return
	 */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHMMssSSS");
	
	public static String createTransactionId(String stockCode) {
		return sdf.format(new Date()) + stockCode;
		
	}

}
