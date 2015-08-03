package stock.util;

import java.math.BigDecimal;
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
	
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
	
	public static String createTransactionId(String stockCode) {
		return sdf.format(new Date()) + stockCode;
		
	}
	
	public static String getDateString(Date date) {
		return sdf1.format(date);
	}
	
	public static String objToStr(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else {
			return "";
		}
	}
	
	public static double round(double source, int scale) {
		return new BigDecimal(source).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
