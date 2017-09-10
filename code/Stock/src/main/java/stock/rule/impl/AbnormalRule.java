package stock.rule.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.model.Stock;
import stock.rule.Rule;
import stock.util.DateUtils;
import stock.vo.DailyPriceVO;

public class AbnormalRule extends BaseRule implements Rule {

	Logger logger = LoggerFactory.getLogger(AbnormalRule.class);
	
	@Override
	public Boolean isSatisfy(Stock stock) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Excecute abnormal rule for stock: " + stock.getLabel() + " and window: " + windowSize);
		List<String> list = DateUtils.getRecentDate(this.windowSize);
		DailyPriceVO day = stock.getDailyPrice().get(list.get(0));
		DailyPriceVO fstDay = stock.getDailyPrice().get(list.get(list.size() - 1));
		if (day.getEndPrice() - fstDay.getEndPrice() > 20) {
			return true;
		} else {
			return false;
		}
	}

	public String getRuleId() {
		// TODO Auto-generated method stub
		return "RULE_HISTORY_4";
	}

}
