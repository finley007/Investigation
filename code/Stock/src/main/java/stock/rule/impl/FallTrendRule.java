package stock.rule.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.model.Stock;
import stock.rule.Rule;
import stock.util.DateUtils;
import stock.util.StockConstants;
import stock.vo.DailyPriceVO;

/**
 * @author liuli
 * 规则描述：连续TREND_WINDOW_SIZE天下跌，市盈率小于20
 */
public class FallTrendRule implements Rule {

	Logger logger = LoggerFactory.getLogger(FallTrendRule.class);
	
	private Boolean isOverCloseTime = false;
	
	public FallTrendRule() {
		this.isOverCloseTime = DateUtils.isOverCloseTime(new Date());
	}
	
	@Override
	public Boolean isSatisfy(Stock info) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = DateUtils.getRecentDate(StockConstants.TREND_WINDOW_SIZE);
		//TODO Consider limit up first
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				DailyPriceVO dayPrice = info.getDailyPrice().get(list.get(i));
				DailyPriceVO lastDayPrice = info.getDailyPrice().get(list.get(i + 1));
				if (dayPrice == null) {
					logger.warn("The data for stock: " + info.getLabel() + " on date: " + list.get(i) + " is not found");
					continue;
				}
				if (lastDayPrice == null) {
					logger.warn("The data for stock: " + info.getLabel() + " on date: " + list.get(i + 1) + " is not found");
					continue;
				}
				if (dayPrice.getEndPrice() >= lastDayPrice.getEndPrice()) {
					return false;
				} 
			}
			DailyPriceVO todayPrice = info.getDailyPrice().get(list.get(0));
//			if (todayPrice.getEndPrice() > 20) {
//				return false;
//			}
			if (info.getPer() > 20) {
				return false;
			}
		}
		return true;
	}

	public String getRuleId() {
		return "RULE_HISTORY_2";
	}
	
}
