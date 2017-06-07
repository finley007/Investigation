package stock.parse.impl.sina;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import stock.model.Stock;
import stock.parse.InfoParser;
import stock.util.StockConstants;
import stock.vo.DailyPriceVO;

public class SinaStockHistoryParser implements InfoParser {
	
	private final Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}\\s+</a>\\s+</div></td>\\s+(<td><div align=\"center\">([0-9]+.[0-9]+)</div></td>\\s+){3}" +
			"(<td class=\"tdr\"><div align=\"center\">([0-9]+.[0-9]+)</div></td>\\s+){3}");
	
	private final Pattern pattern1 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
	private final Pattern pattern2 = Pattern.compile("([0-9]+\\.[0-9]+)|([0-9]{5,})");
	
	@Override
	public void parseStockInfo(Stock stock, String info) throws Exception {
		// TODO Auto-generated method stub
		stock.clearDailyPrice();
		int size = StockConstants.CACHE_DATA_WINDOW_SIZE + 1;
        Matcher matcher = pattern.matcher(info);
        while (matcher.find() && size > 0) {
        	String target = matcher.group();
        	String date = getDate(target);
        	if (!"".equals(date)) {
	        	setDailyPriceInfo(stock, target, date);
	        	size--;
        	}
        }
	}

	private void setDailyPriceInfo(Stock stock, String target, String date) {
		DailyPriceVO vo = new DailyPriceVO(date);
		Matcher matcher2 = pattern2.matcher(target);
		List list = new ArrayList();
		while (matcher2.find()) {
			list.add(matcher2.group());
		}
		vo.setStartPrice(Double.valueOf(list.get(0).toString()));
		vo.setMaxPrice(Double.valueOf(list.get(1).toString()));
		vo.setEndPrice(Double.valueOf(list.get(2).toString()));
		vo.setMinPrice(Double.valueOf(list.get(3).toString()));
		vo.setTransAmout(Long.valueOf(list.get(4).toString()));
		vo.setTransQuantity(Long.valueOf(list.get(5).toString()));
		stock.addDailyPrice(date, vo);
	}

	private String getDate(String target) {
		Matcher matcher1 = pattern1.matcher(target);
		if (matcher1.find()) {
			return matcher1.group();
		} else {
			return "";
		}
	}

}
