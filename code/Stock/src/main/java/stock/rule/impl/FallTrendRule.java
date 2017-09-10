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
 * 规则描述：连续几天下跌，市盈率小于20
 */
public class FallTrendRule extends BaseRule implements Rule {

	Logger logger = LoggerFactory.getLogger(FallTrendRule.class);
	
	private Boolean isOverCloseTime = false;
	
	public FallTrendRule() {
		this.isOverCloseTime = DateUtils.isOverCloseTime(new Date());
	}
	
	@Override
	public Boolean isSatisfy(Stock stock) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Excecute fall trend rule for stock: " + stock.getLabel() + " and window: " + this.windowSize);
		List<String> list = DateUtils.getRecentDate(this.windowSize);
		//TODO Consider limit up first
		if (list != null && list.size() > 0) {
			int comparedNum = 0;
			for (int i = 0; i < list.size() - 1; i++) {
				DailyPriceVO dayPrice = stock.getDailyPrice().get(list.get(i));
				DailyPriceVO lastDayPrice = stock.getDailyPrice().get(list.get(i + 1));
				if (dayPrice == null) {
					logger.warn("The data for stock: " + stock.getLabel() + " on date: " + list.get(i) + " is not found");
					continue;
				}
				if (lastDayPrice == null) {
					logger.warn("The data for stock: " + stock.getLabel() + " on date: " + list.get(i + 1) + " is not found");
					i++;
					continue;
				}
				if (dayPrice.getEndPrice() >= lastDayPrice.getEndPrice()) {
					return false;
				} else {
					comparedNum++;
				}
			}
			//可比较的数据小于总数据的的80%，不予考虑
			if ((comparedNum / (list.size() - 1)) < 0.8) {
				return false;
			}
//			DailyPriceVO todayPrice = stock.getDailyPrice().get(list.get(0));
//			if (todayPrice.getEndPrice() > 20) {
//				return false;
//			}
//			if (stock.getPer() > 20) {
//				return false;
//			}
		}
		return true;
	}

	public String getRuleId() {
		return "RULE_HISTORY_2";
	}
	
}
