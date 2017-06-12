package stock.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import stock.dao.IStockOperation;
import stock.feed.InfoFeeder;
import stock.feed.http.MyStockInfoFeeder;
import stock.manager.StockManager;
import stock.model.Calendar;
import stock.model.MyAction;
import stock.model.MyStock;
import stock.model.RuleItem;
import stock.model.RuleExecuteResult;
import stock.model.RuleExecuteHistory;
import stock.model.Stock;
import stock.timer.TimerConstants;
import stock.util.CommonUtils;
import stock.util.StockConstants;
import stock.vo.AlertVO;
import stock.vo.TransInfoVO;

public class StockManagerImpl implements StockManager {
	
	private IStockOperation stockOperation;
	
	public IStockOperation getStockOperation() {
		return stockOperation;
	}

	public void setStockOperation(IStockOperation stockOperation) {
		this.stockOperation = stockOperation;
	}

	public List<Stock> getStockList() throws Exception {
		// TODO Auto-generated method stub
		return stockOperation.selectAllStock();
	}

	public List<Stock> getStockListByCondition(String condition) throws Exception {
		return stockOperation.selectStockByCondition(condition);
	}
	
	public List<MyStock> getMyCurrentStock() throws Exception {
		List<MyStock> result = stockOperation.selectMyCurrentStock();
		feedStockInfo(result);
		return result;
	}
	
	public MyStock getMyCurrentStockByCode(String stockCode) throws Exception {
		return stockOperation.selectMyCurrentStockByCode(stockCode);
	}
	
	private void feedStockInfo(List<MyStock> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (MyStock myStock : list) {
				InfoFeeder feeder = new MyStockInfoFeeder();
				feeder.feedInfo(myStock.getStock());
				myStock.caculateProfit();
			}
		}
	}
	
	public List<MyStock> getMyEverStockByCode(String stockCode) throws Exception {
		if (StockConstants.ASTERISK.equals(stockCode)) {		
			return stockOperation.selectMyEverStockByCode(null);
		} else {
			return stockOperation.selectMyEverStockByCode(stockCode);
		}
	}

	public MyStock getMyStockByTransId(String transId) throws Exception {
		return stockOperation.selectMyStockByPK(transId);
	}
	
	public void addMyStock(MyStock info) throws Exception {
		String transId = CommonUtils.createTransactionId(info.getStock().getCode());
		info.setTransactionId(transId);
		info.setStatus(StockConstants.MY_STOCK_STATUS_IN);
		info.setIsMonitor(TimerConstants.NOT_MONITOR);
		info.setProfit(0.0);
		info.setProfitRate(0.0);
		stockOperation.insertMyStock(info);
		addAction(info, StockConstants.ACTION_TYPE_BUY);
	}
	
	private void addAction(MyStock info, Integer action) throws Exception {
		MyAction myAction = new MyAction();
		myAction.setActionId(UUID.randomUUID().toString());
		myAction.setTransactionId(info.getTransactionId());
		myAction.setQuantity(info.getQuantity());
		myAction.setPrize(info.getBuyPrice());
		myAction.setTime(info.getOpenTime());
		myAction.setActionType(action);
		stockOperation.insertMyAction(myAction);
	}
	
	public void updateMyStock(MyStock info, Integer action) throws Exception {
		addAction(info, action);
		MyStock myStock = stockOperation.selectMyStockByPK(info.getTransactionId());
		int quantity = 0;
		double newPrice = 0.0;
		double profitRate = 0.0;
		String sql = "";
		if (action == StockConstants.ACTION_TYPE_BUY) {
			//sql = "update my_stock t set t.buy_price = ?, t.quantity = ?, t.status = ? where t.transaction_id = ?";
			quantity = info.getQuantity() + myStock.getQuantity();
			newPrice = (info.getBuyPrice() * info.getQuantity() + myStock.getBuyPrice() * myStock.getQuantity()) / quantity;
			myStock.setBuyPrice(newPrice);
			myStock.setQuantity(quantity);
			myStock.setStatus(StockConstants.MY_STOCK_STATUS_IN);
		} else {
			if (info.getQuantity().equals(myStock.getQuantity())) { //Sold out, then buying price will save the profit
				//sql = "update my_stock t set t.profit = ?, t.quantity = ?, t.status = ?, t.profit_rate = ? where t.transaction_id = ?";
				quantity = 0;
				newPrice = ((info.getBuyPrice() * info.getQuantity() - myStock.getBuyPrice() * myStock.getQuantity()));
				profitRate = newPrice * 100 / (myStock.getBuyPrice() * myStock.getQuantity());
				myStock.setBuyPrice(newPrice);
				myStock.setQuantity(quantity);
				myStock.setStatus(StockConstants.MY_STOCK_STATUS_OUT);
				myStock.setProfit(newPrice);
				myStock.setProfitRate(profitRate);
			} else {
				//sql = "update my_stock t set t.buy_price = ?, t.quantity = ?, t.status = ? where t.transaction_id = ?";
				quantity = myStock.getQuantity() - info.getQuantity();
				newPrice = ((myStock.getBuyPrice() * myStock.getQuantity() - info.getBuyPrice() * info.getQuantity())) / quantity;
				myStock.setBuyPrice(newPrice);
				myStock.setQuantity(quantity);
				myStock.setStatus(StockConstants.MY_STOCK_STATUS_IN);
			}
		}
		stockOperation.updateMyStockByPKSelective(myStock);
	}
	
	public void updateMonitorStatus(String transId, Integer isMonitor) throws Exception {
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement("update my_stock t set t.is_monitor = ? where t.transaction_id = ?");
//		statement.setInt(1, isMonitor);
//		statement.setString(2, transId);
//		statement.execute();
//		conn.close();
	}
	
	public void addAlert(String stockCode, Integer alertType, String message) throws Exception {
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement("insert into monitor_alert values (?,?,?,?,?,?)");
//		String alertId = UUID.randomUUID().toString();
//		statement.setString(1, alertId);
//		statement.setInt(2, StockConstants.ALERT_TYPE_STOCK_DROP);
//		statement.setString(3, stockCode);
//		statement.setString(4, message);
//		statement.setInt(5, StockConstants.ALERT_STATUS_TO_NOTICE);
//		statement.setTimestamp(6, Timestamp.valueOf(CommonUtils.getDateString(new Date())));
//		statement.execute();
//		conn.close();
	}
	
	public List<AlertVO> readAlert() throws Exception {
//		List<AlertVO> result = new ArrayList<AlertVO>();
//		Connection conn = getConnection();
//		Statement statement = conn.createStatement();
//		String sql = "select * from monitor_alert t where t.status = " + StockConstants.ALERT_STATUS_TO_NOTICE + " order by t.alert_time desc";
//		ResultSet rs = statement.executeQuery(sql);  
//		while (rs.next()) {
//			AlertVO alertVO = new AlertVO();
//			String alertId = rs.getString("alert_id");
//			Integer alertType = rs.getInt("alert_type");
//			String stockCode = rs.getString("stock_code");
//			String message = rs.getString("message");
//			Integer status = rs.getInt("status");
//			Date alertTime = rs.getDate("alert_time");
//			alertVO.setAlertId(alertId);
//			alertVO.setAlertType(alertType);
//			alertVO.setStockCode(stockCode);
//			alertVO.setMessage(message);
//			result.add(alertVO);
//		}  
//		rs.close();  
//		conn.close();
//		return result;
		return null;
	}
	
	public void updateAlertStatus(String alertId, Integer status) throws Exception {
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement("update monitor_alert t set t.status = ? where t.alert_id = ?");
//		statement.setInt(1, status);
//		statement.setString(2, alertId);
//		statement.execute();
//		conn.close();
	}
	
	public String addRuleExecuteHistory(String ruleId, String historyId, Integer result, Long timeCost, Integer stockNum) throws Exception {
		RuleExecuteHistory history = new RuleExecuteHistory();
		history.setId(historyId);
		history.setRuleId(ruleId);
		history.setRunTime(new Date());
		history.setResult(result);
		history.setTimeCost(timeCost.intValue());
		history.setStockNum(stockNum);
		stockOperation.insertRuleExecuteHistory(history);
		return historyId;
	}
	
	public void addRuleExecuteResult(String historyId, String stockCode) throws Exception {
		RuleExecuteResult ruleExecuteResult = new RuleExecuteResult();
		ruleExecuteResult.setId(UUID.randomUUID().toString());
		ruleExecuteResult.setHistoryId(historyId);
		ruleExecuteResult.setStockCode(stockCode);
		stockOperation.insertRuleExecuteResult(ruleExecuteResult);
	}
	
	public List<Stock> getRuleResultByHistoryId(String historyId) throws Exception {
		return stockOperation.selectRuleExecuteResultByHistoryId(historyId);
	}
	
	public void updateRuleResultTrend(String historyId, String stockCode, Integer day, Double profit) throws Exception {
//		Connection conn = getConnection();
//		String dayCol = "";
//		if (day.equals(StockConstants.FIRST_DAY_RESULT)) {
//			dayCol = "first_day_trend";
//		} else if (day.equals(StockConstants.SECOND_DAY_RESULT)) {
//			dayCol = "second_day_trend";
//		} else if (day.equals(StockConstants.THIRD_DAY_RESULT)) {
//			dayCol = "third_day_trend";
//		} else {
//			return;
//		}
//		String sql = "update rule_result t set t." + dayCol + " = ? where t.history_id = ? and t.stock_code = ?";
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setDouble(1, profit);
//		statement.setString(2, historyId);
//		statement.setString(3, stockCode);
//		statement.execute();
//		conn.close();
	}
	
	public List<RuleItem> getRuleItemByType(Integer type) throws Exception {
		return stockOperation.selectRuleItemByType(type);
	}
	
	public void saveOrUpdateRuleItem(RuleItem ruleItem) throws Exception {
		if (ruleItem != null && ruleItem.getId() != null && !"".equals(ruleItem.getId())) {
			RuleItem curRuleItem = stockOperation.selectRuleItemByPK(ruleItem);
			if (curRuleItem != null) {
				stockOperation.updateRuleItemByPK(ruleItem);
			} else {
				stockOperation.insertRuleItem(ruleItem);
			}
		} else {
			throw new Exception("Rule item is null");
		}
	}
	
	public void deleteRuleItem(RuleItem ruleItem) throws Exception {
		stockOperation.deleteRuleItemByPK(ruleItem);
	}
	
	public RuleItem getRuleItemByPK(RuleItem ruleItem) throws Exception {
		return stockOperation.selectRuleItemByPK(ruleItem);
	}
	
	public Map<String, Integer> initDateStatus() throws Exception {
		Map<String, Integer> result = new HashMap<String, Integer>();
		List<Calendar> list = stockOperation.selectAllCalendar();
		for (Calendar cal : list) {
			result.put(StockConstants.sdf_date.format(cal.getDate()), cal.getIsValid());
		}
		return result;
	}
	
	public List<RuleExecuteHistory> getRuleExecuteHistoryByRuleId(String ruleId) throws Exception {
		return stockOperation.selectRuleExecuteHistoryByRuleId(ruleId);
	}
	
	public List<Stock> getStockCategory() throws Exception {
		return stockOperation.selectAllMyStock();
	}
	
	public List<MyAction> getActionByTransId(String transId) throws Exception {
		return stockOperation.selectActionByTransId(transId);
	}
	
	public TransInfoVO queryTransInfo(String stockCode) throws Exception {
//		TransInfoVO result = new TransInfoVO();
//		Connection conn = getConnection();
//		String sql = "select count(*) as count, sum(t.PROFIT) as total_profit, avg(t.PROFIT) as avg_profit, avg(t.PROFIT_RATE) as avg_profit_rate, " +
//				"max(t.PROFIT) as max_profit, max(t.PROFIT_RATE) as max_profit_rate, " +
//					"min(t.PROFIT) as min_profit, min(t.PROFIT_RATE) as min_profit_rate, avg(t.CLOSE_TIME - t.OPEN_TIME) as days " +
//						"from my_stock t where t.`STATUS` = 0 " + filterByStockCode(stockCode);
//		PreparedStatement statement = conn.prepareStatement(sql);
//		ResultSet rs = statement.executeQuery();
//		Integer totalCount = 0;
//		Integer gainCount = 0;
//		if (rs.next()) {
//			totalCount = rs.getInt("count");
//			result.setTotalProfit(rs.getDouble("total_profit"));
//			result.setAvgProfit(rs.getDouble("avg_profit"));
//			result.setAvgProfitRate(rs.getDouble("avg_profit_rate"));
//			result.setMaxProfit(rs.getDouble("max_profit"));
//			result.setMaxProfitRate(rs.getDouble("max_profit_rate"));
//			result.setMaxLoss(rs.getDouble("min_profit"));
//			result.setMaxLossRate(rs.getDouble("min_profit_rate"));
//			result.setDays(rs.getInt("days"));
//		}
//		sql = "select count(*) as count from my_stock t where t.`STATUS` = 0 and t.profit > 0 " + filterByStockCode(stockCode);
//		statement = conn.prepareStatement(sql);
//		rs = statement.executeQuery();
//		if (rs.next()) {
//			gainCount = rs.getInt("count");
//		}
//		result.setTotalCount(totalCount);
//		result.setGainCount(gainCount);
//		if (totalCount > gainCount) {
//			result.setLossCount(totalCount - gainCount);
//		} else {
//			result.setLossCount(0);
//		}
//		return result;
		return null;
	}
	
}
 