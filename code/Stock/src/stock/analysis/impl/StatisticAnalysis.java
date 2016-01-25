package stock.analysis.impl;

import java.util.Date;
import java.util.List;

import stock.analysis.StockAnalysis;
import stock.feed.http.HTTPFeeder;
import stock.feed.http.sina.SinaHTTPHistoryFeeder;
import stock.manager.StockManager;
import stock.manager.impl.StockManagerImpl;
import stock.model.MyStock;
import stock.model.Stock;
import stock.util.DateUtils;
import stock.util.StockConstants;
import stock.vo.DailyPriceVO;

public class StatisticAnalysis implements StockAnalysis {

	@Override
	public void doAnalysis(String historyId, int init, int size)
			throws Exception {
		// TODO Auto-generated method stub
		StockManager query = new StockManagerImpl();
		List<Stock> list = query.getRuleResultByHistoryId(historyId);
		int riseNum = 0;
		for (Stock stock : list) {
			HTTPFeeder feeder = new SinaHTTPHistoryFeeder();
			feeder.feedInfo(new MyStock());
			DailyPriceVO todayPriceVO = stock.getDailyPrice().get(StockConstants.sdf_date.format(new Date()));
			DailyPriceVO yesterdayPriceVO = stock.getDailyPrice().get(StockConstants.sdf_date.format(getLastTransactionDay()));
			if (todayPriceVO != null && yesterdayPriceVO != null) {
				Double profit = (todayPriceVO.getEndPrice() - yesterdayPriceVO.getEndPrice())/yesterdayPriceVO.getEndPrice();
				if (profit > 0) {
					riseNum++;
				}
				query.updateRuleResultTrend(historyId, stock.getCode(), 1, profit);
			}
		}
		System.out.println("Total stock number: " + list.size());
		System.out.println("Rise number: " + riseNum);
		System.out.println("Rise rate: " + (riseNum * 100) / list.size() + "%");
	}
	
	private Date getLastTransactionDay() {
		int n = 1;
		while (DateUtils.isWeekEnd(DateUtils.getDayBeforeNDays(new Date(), n))) {
			n++;
		}
		return DateUtils.getDayBeforeNDays(new Date(), n);
	}

}
