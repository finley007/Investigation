package stock.rule.impl;

import java.util.List;

import stock.rule.Rule;
import stock.util.DateUtils;
import stock.vo.DailyPriceVO;
import stock.vo.StockInfo;

public class TrendRule implements Rule {
	
	@Override
	public Boolean isSatisfy(StockInfo info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = DateUtils.getRecentDate();
		//TODO Consider limit up first
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				DailyPriceVO day = info.getDailyPrice().get(list.get(i));
				DailyPriceVO lastDay = info.getDailyPrice().get(list.get(i + 1));
				if (day.getEndPrice() < day.getStartPrice()) {
					return false;
				} else if (day.getEndPrice().equals(day.getStartPrice())
						&& day.getEndPrice() < lastDay.getEndPrice() * 1.09) { //limit up
					return false;
				}
			}
			DailyPriceVO today = info.getDailyPrice().get(list.get(0));
			DailyPriceVO lastDay = info.getDailyPrice().get(list.get(1));
			if ((today.getEndPrice() - lastDay.getEndPrice())/lastDay.getEndPrice() < 0.05) {
				return false;
			}
			Double range = lastDay.getEndPrice() * 0.1 * 2;
			if ((today.getEndPrice() < lastDay.getEndPrice() * 1.09)
					&& ((today.getEndPrice() - today.getStartPrice()) / range) <= 0.4) {
				return false;
			}
			if (today.getEndPrice() > 20) {
				return false;
			}
		}
		return true;
	}
	
}
