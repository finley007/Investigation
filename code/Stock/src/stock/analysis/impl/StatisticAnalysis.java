package stock.analysis.impl;

import java.util.Date;
import java.util.List;

import stock.analysis.StockAnalysis;
import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.db.connect.impl.MysqlConnector;
import stock.db.impl.DBQueryImpl;
import stock.http.HTTPQuery;
import stock.http.impl.sina.SinaHTTPHistoryQuery;
import stock.util.DateUtils;
import stock.util.StockConstants;
import stock.vo.DailyPriceVO;
import stock.vo.Stock;
import stock.vo.StockInfo;

public class StatisticAnalysis implements StockAnalysis {

	@Override
	public void doAnalysis(String historyId, int init, int size)
			throws Exception {
		// TODO Auto-generated method stub
		DBConnector connector = new MysqlConnector();
		DBQuery query = new DBQueryImpl();
		query.setConn(connector);
		List<Stock> list = query.getRuleResultByHistoryId(historyId);
		int riseNum = 0;
		for (Stock stock : list) {
			StockInfo info = new StockInfo(stock);
			HTTPQuery httpQuery = new SinaHTTPHistoryQuery();
			httpQuery.richStockInfo(info);
			DailyPriceVO todayPriceVO = info.getDailyPrice().get(StockConstants.sdf_date.format(new Date()));
			DailyPriceVO yesterdayPriceVO = info.getDailyPrice().get(StockConstants.sdf_date.format(getLastTransactionDay()));
			if (todayPriceVO != null && yesterdayPriceVO != null) {
				Double profit = (todayPriceVO.getEndPrice() - yesterdayPriceVO.getEndPrice())/yesterdayPriceVO.getEndPrice();
				if (profit > 0) {
					riseNum++;
				}
				query.updateRuleResultTrend(historyId, info.getCode(), 1, profit);
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
