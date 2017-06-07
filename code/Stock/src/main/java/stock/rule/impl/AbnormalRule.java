package stock.rule.impl;

import java.util.List;

import stock.model.Stock;
import stock.rule.Rule;
import stock.util.DateUtils;
import stock.vo.DailyPriceVO;

public class AbnormalRule implements Rule {
	
	private static final Integer ABNORMAL_WINDOW_SIZE = 10;

	@Override
	public Boolean isSatisfy(Stock info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = DateUtils.getRecentDate(ABNORMAL_WINDOW_SIZE);
		DailyPriceVO day = info.getDailyPrice().get(list.get(0));
		DailyPriceVO fstDay = info.getDailyPrice().get(list.get(list.size() - 1));
		if (day.getEndPrice() - fstDay.getEndPrice() > 20) {
			return true;
		} else {
			return false;
		}
	}

}
