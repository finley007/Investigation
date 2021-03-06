package stock.util;

import java.text.SimpleDateFormat;

public class StockConstants {
	
	public static final SimpleDateFormat sdf_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String ASTERISK = "*";
	
	public static final Integer PRICE_SCALE = 2;
	
	public static final Integer CACHE_DATA_WINDOW_SIZE = 30;
	public static final Integer DEFAULT_TREND_WINDOW_SIZE = 3;
	
	public static final Integer RESULT_STATUS_SUCCESS = 0;
	public static final Integer RESULT_STATUS_FAIL = 1;
	
	public static final Integer MY_STOCK_STATUS_IN = 1;
	public static final Integer MY_STOCK_STATUS_OUT = 0;

	public static final Integer ACTION_TYPE_BUY = 1;
	public static final Integer ACTION_TYPE_SELL = 0;

	public static final Integer ALERT_TYPE_STOCK_DROP = 0;
	
	public static final Integer ALERT_STATUS_TO_NOTICE = 0;
	public static final Integer ALERT_STATUS_NOTICED = 1;
	
	public static final Integer FIRST_DAY_RESULT = 1;
	public static final Integer SECOND_DAY_RESULT = 2;
	public static final Integer THIRD_DAY_RESULT = 3;
	
	public static final Integer REACH_LIMIT_UP = 1;
	public static final Integer REACH_LIMIT_DOWN = -1;
	public static final Integer NO_REACH_LIMIT = 0;
	
	public static final Integer VALID_DAY = 1; 
	public static final Integer INVALID_DAY = 0; 
}
