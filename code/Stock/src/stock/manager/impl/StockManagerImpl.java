package stock.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import stock.dao.IStockOperation;
import stock.feed.InfoFeeder;
import stock.feed.http.MyStockInfoFeeder;
import stock.manager.StockManager;
import stock.model.MyAction;
import stock.model.MyStock;
import stock.model.Stock;
import stock.timer.TimerConstants;
import stock.util.CommonUtils;
import stock.util.StockConstants;
import stock.vo.AlertVO;
import stock.vo.RuleItemVO;
import stock.vo.RuleRunHistoryVO;
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
				feeder.feedInfo(myStock);
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
	
	public String addRuleHistory(String ruleId, String historyId, Integer result, Long timeCost, Integer stockNum) throws Exception {
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement("insert into rule_run_history values (?,?,?,?,?,?)");
//		statement.setString(1, historyId);
//		statement.setString(2, ruleId);
//		statement.setTimestamp(3, Timestamp.valueOf(CommonUtils.getDateString(new Date())));
//		statement.setInt(4, result);
//		statement.setLong(5, timeCost);
//		statement.setInt(6, stockNum);
//		statement.execute();
//		conn.close();
//		return historyId;
		return null;
	}
	
	public void addRuleResult(String historyId, String stockCode) throws Exception {
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement("insert into rule_result values (?,?,?,?,?,?)");
//		String id = UUID.randomUUID().toString();
//		statement.setString(1, id);
//		statement.setString(2, historyId);
//		statement.setString(3, stockCode);
//		statement.setDouble(4, 0.0);
//		statement.setDouble(5, 0.0);
//		statement.setDouble(6, 0.0);
//		statement.execute();
//		conn.close();
	}
	
	public List<Stock> getRuleResultByHistoryId(String historyId) throws Exception {
//		List<Stock> result = new ArrayList<Stock>();
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement("select * from rule_result t where t.history_id = ? order by stock_code");
//		statement.setString(1, historyId);
//		ResultSet rs = statement.executeQuery();  
//		while (rs.next()) {
//			Stock stock = new Stock();
//			stock.setCode(rs.getString("stock_code"));
//			result.add(stock);
//		}  
//		rs.close();  
//		conn.close();
//		return result;
		return null;
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
	
	public List<RuleItemVO> getRuleItemByType(Integer type) throws Exception {
//		List<RuleItemVO> result = new ArrayList<RuleItemVO>();
//		Connection conn = getConnection();
//		String sql = "select * from rule_item t where t.TYPE = ? order by t.ID";
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setInt(1, type);
//		ResultSet rs = statement.executeQuery();  
//		while (rs.next()) {
//			RuleItemVO ruleItem = new RuleItemVO();
//			ruleItem.setId(rs.getString("id"));
//			ruleItem.setName(rs.getString("name"));
//			ruleItem.setType(rs.getInt("type"));
//			ruleItem.setImplClz(rs.getString("impl_class"));
//			ruleItem.setDesp(rs.getString("description"));
//			result.add(ruleItem);
//		}  
//		return result;
		return null;
	}
	
	public void saveOrUpdateRuleItem(RuleItemVO ruleItem) throws Exception {
//		if (ruleItem != null && ruleItem.getId() != null 
//				&& !"".equals(ruleItem.getId())) {
//			Connection conn = getConnection();
//			String sql = "select * from rule_item t where t.id = ? and t.type = ?";
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, ruleItem.getId());
//			statement.setInt(2, ruleItem.getType());
//			ResultSet rs = statement.executeQuery();
//			if (rs.next()) {
//				String update = "update rule_item t set t.name = ?, t.impl_class = ?, t.description = ? where t.id = ? and t.type = ?";
//				statement = conn.prepareStatement(update);
//				statement.setString(1, ruleItem.getName());
//				statement.setString(2, ruleItem.getImplClz());
//				statement.setString(3, ruleItem.getDesp());
//				statement.setString(4, ruleItem.getId());
//				statement.setInt(5, ruleItem.getType());
//				statement.execute();
//			} else {
//				String insert = "insert into rule_item values (?, ?, ?, ?, ?)";
//				statement = conn.prepareStatement(insert);
//				statement.setString(1, ruleItem.getId());
//				statement.setString(2, ruleItem.getName());
//				statement.setInt(3, ruleItem.getType());
//				statement.setString(4, ruleItem.getImplClz());
//				statement.setString(5, ruleItem.getDesp());
//				statement.execute();
//			}
//		} else {
//			throw new Exception("Rule item is null");
//		}
	}
	
	public void deleteRuleItem(RuleItemVO ruleItem) throws Exception {
//		if (ruleItem != null && ruleItem.getId() != null 
//				&& !"".equals(ruleItem.getId())) {
//			Connection conn = getConnection();
//			String sql = "delete from rule_item where id = ? and type = ?";
//			PreparedStatement statement = conn.prepareStatement(sql);
//			statement.setString(1, ruleItem.getId());
//			statement.setInt(2, ruleItem.getType());
//			statement.execute();
//		}
	}
	
	public RuleItemVO getRuleItemById(String ruleId) throws Exception {
//		RuleItemVO ruleItem = new RuleItemVO();
//		Connection conn = getConnection();
//		String sql = "select * from rule_item t where t.id = ?";
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setString(1, ruleId);
//		ResultSet rs = statement.executeQuery();  
//		if (rs.next()) {
//			ruleItem.setId(rs.getString("id"));
//			ruleItem.setName(rs.getString("name"));
//			ruleItem.setType(rs.getInt("type"));
//			ruleItem.setImplClz(rs.getString("impl_class"));
//			ruleItem.setDesp(rs.getString("description"));
//		}  
//		return ruleItem;
		return null;
	}
	
	public void clearCalendar(Date start, Date end) throws Exception {
//		String sql = "delete from calendar where date >= ? and date <= ?";
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setDate(1, new java.sql.Date(start.getTime()));
//		statement.setDate(2, new java.sql.Date(end.getTime()));
//		statement.execute();
	}
	
	public void insertCalendar(Date date, Integer status) throws Exception {
//		String sql = "insert into calendar values (?, ?)";
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setDate(1, new java.sql.Date(date.getTime()));
//		statement.setInt(2, status);
//		statement.execute();
	}
	
	public Map<String, Integer> initDateStatus() throws Exception {
//		Map<String, Integer> result = new HashMap<String, Integer>();
//		String sql = "select * from calendar order by date";
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement(sql);
//		ResultSet rs = statement.executeQuery();  
//		if (rs.next()) {
//			result.put(StockConstants.sdf_date.format(rs.getDate(1)), rs.getInt(2));
//		}
//		return result;
		return null;
	}
	
	public List<RuleRunHistoryVO> getRuleRunHistoryByRuleId(String ruleId) throws Exception {
//		List<RuleRunHistoryVO> result = new ArrayList<RuleRunHistoryVO>();
//		String sql = "select * from rule_run_history t where t.rule_id = ? order by t.run_time desc";
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setString(1, ruleId);
//		ResultSet rs = statement.executeQuery();  
//		while (rs.next()) {
//			RuleRunHistoryVO vo = new RuleRunHistoryVO();
//			vo.setId(rs.getString("id"));
//			vo.setTime(rs.getDate("run_time"));
//			vo.setStockNum(rs.getInt("stock_num"));
//			result.add(vo);
//		}
//		return result;
		return null;
	}
	
	public List<Stock> getRuleResultByRuleHisId(String hisId) throws Exception {
//		List<Stock> result = new ArrayList<Stock>();
//		String sql = "select * from rule_result t where t.history_id = ? order by t.stock_code";
//		Connection conn = getConnection();
//		PreparedStatement statement = conn.prepareStatement(sql);
//		statement.setString(1, hisId);
//		ResultSet rs = statement.executeQuery();  
//		while (rs.next()) {
//			Stock stock = new Stock();
//			String code = rs.getString("stock_code");
//			stock.setCode(code);
//			stock.setName(StockCache.getNameByCode(code));
//			result.add(stock);
//		}
//		return result;
		return null;
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
	
	private String filterByStockCode(String stockCode) {
		if (stockCode != null && !"".equals(stockCode)) {
			return " and t.STOCK_CODE = '" + stockCode + "' ";
		} else {
			return "";
		}
	}
}
 