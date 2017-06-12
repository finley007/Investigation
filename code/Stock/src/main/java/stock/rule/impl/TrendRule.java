package stock.rule.impl;

import java.util.List;

import stock.model.Stock;
import stock.rule.Rule;
import stock.util.DateUtils;
import stock.util.StockConstants;
import stock.vo.DailyPriceVO;

/**
 * @author liuli
 * 
 * 1. Continuous n days end price is larger than start price.
 * 2. Rise smaller than half of max rise limit(10%). 
 * 3. Input flow should larger than 1.5 output flow.
 */
public class TrendRule implements Rule {
	
	@Override
	public Boolean isSatisfy(Stock info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = DateUtils.getRecentDate(StockConstants.TREND_WINDOW_SIZE);
		if (list != null && list.size() > 0) {
			// #1
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
//			// #2
//			DailyPriceVO today = info.getDailyPrice().get(list.get(0));
//			DailyPriceVO lastDay = info.getDailyPrice().get(list.get(1));
//			if ((today.getEndPrice() - lastDay.getEndPrice())/lastDay.getEndPrice() < 0.05) {
//				return false;
//			}
//			// #3
//			if (info.getMainInflow() < 1.5 * info.getMainOutflow()) {
//				return false;
//			}
//			if (today.getEndPrice() > 20) {
//				return false;
//			}
		}
		return true;
	}

	public String getRuleId() {
		return "RULE_HISTORY_1";
	}
	
}
