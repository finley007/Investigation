package stock.rule.impl;

import java.util.Date;
import java.util.List;

import stock.rule.Rule;
import stock.util.DateUtils;
import stock.vo.DailyPriceVO;
import stock.vo.StockInfo;

public class FallTrendRule implements Rule {
	
	private Boolean isOverCloseTime = false;
	
	public FallTrendRule() {
		this.isOverCloseTime = DateUtils.isOverCloseTime(new Date());
	}
	
	@Override
	public Boolean isSatisfy(StockInfo info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = DateUtils.getRecentDate();
		//TODO Consider limit up first
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				DailyPriceVO day = info.getDailyPrice().get(list.get(i));
				DailyPriceVO lastDay = info.getDailyPrice().get(list.get(i + 1));
				if (day.getEndPrice() > day.getStartPrice()) {
					return false;
				} else if (day.getEndPrice().equals(day.getStartPrice())
						&& day.getEndPrice() > lastDay.getEndPrice() * 0.89) { //limit down
					return false;
				}
			}
			DailyPriceVO today = info.getDailyPrice().get(list.get(0));
			if (today.getEndPrice() > 20) {
				return false;
			}
		}
		return true;
	}
	
}
