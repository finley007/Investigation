package stock.rule.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.model.Stock;
import stock.rule.Rule;
import stock.util.DateUtils;
import stock.vo.DailyPriceVO;

public class BandRule extends BaseRule implements Rule {

	Logger logger = LoggerFactory.getLogger(BandRule.class);
	
	@Override
	public Boolean isSatisfy(Stock stock) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Excecute band rule for stock: " + stock.getLabel() + " and window: " + windowSize);
		List<String> list = DateUtils.getRecentDate(this.windowSize);
		if (list != null && list.size() > 0) {
			Double minPrice = 10000.0;
			for (int i = 0; i < list.size() - 1; i++) {
				DailyPriceVO lastDay = stock.getDailyPrice().get(list.get(i + 1));
				if (minPrice > lastDay.getEndPrice()) {
					minPrice = lastDay.getEndPrice();
				}
			}
			DailyPriceVO today = stock.getDailyPrice().get(list.get(0));
			if (today.getEndPrice() > minPrice) {
				return false;
			}
		}
		return true;
	}

	public String getRuleId() {
		// TODO Auto-generated method stub
		return "RULE_HISTORY_3";
	}

}
