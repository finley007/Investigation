package stock.rule.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stock.date.DateUtils;
import stock.rule.Rule;
import stock.util.StockConstants;
import stock.vo.DailyPriceVO;
import stock.vo.StockInfo;

public class TrendRule implements Rule {
	
	@Override
	public Boolean isSatisfy(StockInfo info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = getRecentDate();
		if (list != null && list.size() > 0) {
			for (String date : list) {
				DailyPriceVO vo = info.getDailyPrice().get(date);
				if (vo.getEndPrice() < vo.getStartPrice()) {
					return false;
				}
			}
			DailyPriceVO vo1 = info.getDailyPrice().get(list.get(0));
			DailyPriceVO vo2 = info.getDailyPrice().get(list.get(list.size() - 1));
			if ((vo1.getEndPrice() - vo2.getEndPrice())/vo2.getEndPrice() < 0.1) {
				return false;
			}
			if (vo1.getEndPrice() > 20) {
				return false;
			}
		}
		return true;
	}
	
	List<String> getRecentDate() {
		List<String> result = new ArrayList<String>();
		int beforeDays = 0;
		for (int i = 0; i < StockConstants.HISTORY_DAILY_INFO_SIZE; i++) {
			Date date = null;
			do {
				date = DateUtils.getDayBeforeNDays(new Date(), ++beforeDays);
			} while (DateUtils.isWeekEnd(date));
			result.add(StockConstants.sdf_date.format(date));
		}
		return result;
	}

}
