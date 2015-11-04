package stock.rule.impl;

import java.util.List;

import stock.rule.Rule;
import stock.util.DateUtils;
import stock.vo.DailyPriceVO;
import stock.vo.StockInfo;

public class BandRule implements Rule {
	
	private static final Integer BIND_WINDOW_SIZE = 3;

	@Override
	public Boolean isSatisfy(StockInfo info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = DateUtils.getRecentDate(BIND_WINDOW_SIZE);
		if (list != null && list.size() > 0) {
			Double minPrice = 10000.0;
			for (int i = 0; i < list.size() - 1; i++) {
				DailyPriceVO lastDay = info.getDailyPrice().get(list.get(i + 1));
				if (minPrice > lastDay.getEndPrice()) {
					minPrice = lastDay.getEndPrice();
				}
			}
			DailyPriceVO today = info.getDailyPrice().get(list.get(0));
			if (today.getEndPrice() > minPrice) {
				return false;
			}
		}
		return true;
	}

}
