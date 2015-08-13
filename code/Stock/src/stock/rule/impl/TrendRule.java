package stock.rule.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stock.rule.Rule;
import stock.util.DateUtils;
import stock.util.StockConstants;
import stock.vo.DailyPriceVO;
import stock.vo.StockInfo;

public class TrendRule implements Rule {
	
	private Boolean isOverCloseTime = false;
	
	public TrendRule() {
		this.isOverCloseTime = DateUtils.isOverCloseTime(new Date());
	}
	
	@Override
	public Boolean isSatisfy(StockInfo info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = getRecentDate();
		//TODO Consider limit up first
		if (list != null && list.size() > 0) {
			for (String date : list) {
				DailyPriceVO vo = info.getDailyPrice().get(date);
				if (vo.getEndPrice() < vo.getStartPrice()) {
					return false;
				}
			}
			DailyPriceVO vo1 = info.getDailyPrice().get(list.get(0));
			DailyPriceVO vo2 = info.getDailyPrice().get(list.get(1));
			if ((vo1.getEndPrice() - vo2.getEndPrice())/vo2.getEndPrice() < 0.05) {
				return false;
			}
			Double range = vo2.getEndPrice() * 0.1 * 2;
			if (((vo1.getEndPrice() - vo1.getStartPrice()) / range) < 0.5
					|| vo1.getEndPrice() == vo1.getStartPrice()) {
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
		if (this.isOverCloseTime) {
			beforeDays --;
		}
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
