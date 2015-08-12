package stock.util;

import java.text.SimpleDateFormat;

public class StockConstants {
	
	public static final Integer HISTORY_DAILY_INFO_SIZE = 3;
	
	public static final Integer RESULT_STATUS_SUCCESS = 0;
	public static final Integer RESULT_STATUS_FAIL = 1;
	
	public static final Integer MY_STOCK_STATUS_IN = 1;
	public static final Integer MY_STOCK_STATUS_OUT = 0;

	public static final SimpleDateFormat sdf_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final Integer ACTION_TYPE_BUY = 1;
	public static final Integer ACTION_TYPE_SELL = 0;

	public static final Integer ALERT_TYPE_STOCK_DROP = 0;
	
	public static final Integer ALERT_STATUS_TO_NOTICE = 0;
	public static final Integer ALERT_STATUS_NOTICED = 1;
}
