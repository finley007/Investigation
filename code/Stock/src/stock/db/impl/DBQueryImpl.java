package stock.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import stock.db.DBQuery;
import stock.db.connect.DBConnector;
import stock.timer.TimerConstants;
import stock.util.CommonUtils;
import stock.util.StockConstants;
import stock.vo.AlertVO;
import stock.vo.MyStockInfo;
import stock.vo.RuleItemVO;
import stock.vo.Stock;

public class DBQueryImpl implements DBQuery {
	
	private DBConnector connector;

	public DBConnector getConn() {
		return connector;
	}

	public void setConn(DBConnector connector) {
		this.connector = connector;
	}
	
	private Connection getConnection() throws Exception {
		Connection conn = null;
		if (connector != null) {
			conn = this.connector.getConnection();
		}
		if (conn != null && !conn.isClosed()) {
			return conn;
		} else {
			throw new Exception("DB connection init error!!");
		}
	}

	public List<Stock> getStockList() throws Exception {
		// TODO Auto-generated method stub
		List<Stock> result = new ArrayList<Stock>();
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from all_stock order by code";
		ResultSet rs = statement.executeQuery(sql);  
		while (rs.next()) {
			Stock stock = new Stock();
			stock.setCode(rs.getString("code"));
			stock.setName(rs.getString("name"));
			result.add(stock);
		}  
		rs.close();  
		conn.close();
		return result;
	}
	
	public List<MyStockInfo> getMyStock() throws Exception {
		List<MyStockInfo> result = new ArrayList<MyStockInfo>();
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from my_stock t where t.status = 1 order by t.stock_code";
		ResultSet rs = statement.executeQuery(sql);  
		while (rs.next()) {
			MyStockInfo stock = initStock(rs);
			result.add(stock);
		}  
		rs.close();  
		conn.close();
		return result;
	}

	private MyStockInfo initStock(ResultSet rs) throws SQLException {
		MyStockInfo stock = new MyStockInfo();
		String code = rs.getString("stock_code");
		String transId = rs.getString("transaction_id");
		Double price = rs.getDouble("buy_price");
		Integer quantity = rs.getInt("quantity");
		Date date = rs.getDate("buy_time");
		Integer isMonitor = rs.getInt("is_monitor");
		stock.setCode(code);
		stock.setTransId(transId);
		stock.setBuyingPrice(price);
		stock.setQuantity(quantity);
		stock.setBuyingTime(date);
		stock.setIsMonitor(isMonitor);
		return stock;
	}
	
	public MyStockInfo getMyStockByTransId(String transId) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("select * from my_stock t where t.transaction_id = ?");
		statement.setString(1, transId);
		ResultSet rs = statement.executeQuery();  
		if (rs.next()) {
			return initStock(rs);
		}  
		rs.close();  
		conn.close();
		return null;
	}
	
	public MyStockInfo getMyStockByCode(String code) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("select * from my_stock t where t.STOCK_CODE = ? and t.STATUS = 1");
		statement.setString(1, code);
		ResultSet rs = statement.executeQuery();  
		if (rs.next()) {
			return initStock(rs);
		}  
		rs.close();  
		conn.close();
		return null;
	}
	
	public void addMyStock(MyStockInfo info) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("insert into my_stock values (?,?,?,?,?,?,?)");
		String transId = CommonUtils.createTransactionId(info.getCode());
		info.setTransId(transId);
		statement.setString(1, transId);
		statement.setString(2, info.getCode());
		statement.setDouble(3, info.getBuyingPrice());
		statement.setInt(4, info.getQuantity());
		statement.setTimestamp(5, Timestamp.valueOf(CommonUtils.getDateString(info.getBuyingTime())));
		statement.setInt(6, StockConstants.MY_STOCK_STATUS_IN);
		statement.setInt(7, TimerConstants.NOT_MONITOR);
		statement.execute();
		addAction(conn, info, StockConstants.ACTION_TYPE_BUY);
		conn.close();
	}
	
	private void addAction(Connection conn, MyStockInfo info, Integer action) throws Exception {
		PreparedStatement statement = conn.prepareStatement("insert into my_action values (?,?,?,?,?,?)");
		statement.setString(1, info.getTransId());
		statement.setString(2, UUID.randomUUID().toString());
		statement.setInt(3, info.getQuantity());
		statement.setDouble(4, info.getBuyingPrice());
		statement.setTimestamp(5, Timestamp.valueOf(CommonUtils.getDateString(info.getBuyingTime())));
		statement.setInt(6, action);
		statement.execute();
	}
	
	public void updateMyStock(MyStockInfo info, Integer action) throws Exception {
		Connection conn = getConnection();
		addAction(conn, info, action);
		MyStockInfo curInfo = getMyStockByTransId(info.getTransId());
		PreparedStatement statement = conn.prepareStatement("update my_stock t set t.buy_price = ?, t.quantity = ?, t.status = ? where t.transaction_id = ?");
		int status = StockConstants.MY_STOCK_STATUS_IN;
		int quantity = 0;
		double buyingPrice = 0.0;
		if (action == StockConstants.ACTION_TYPE_BUY) {
			quantity = info.getQuantity() + curInfo.getQuantity();
			buyingPrice = (info.getBuyingPrice() * info.getQuantity() + curInfo.getBuyingPrice() * curInfo.getQuantity()) / quantity;
		} else {
			if (info.getQuantity().equals(curInfo.getQuantity())) { //Sold out, then buying price will save the profit
				status = StockConstants.MY_STOCK_STATUS_OUT;
				quantity = 0;
				buyingPrice = ((info.getBuyingPrice() * info.getQuantity() - curInfo.getBuyingPrice() * curInfo.getQuantity()));
			} else {
				quantity = curInfo.getQuantity() - info.getQuantity();
				buyingPrice = ((curInfo.getBuyingPrice() * curInfo.getQuantity() - info.getBuyingPrice() * info.getQuantity())) / quantity;
			}
		}
		statement.setDouble(1, buyingPrice);
		statement.setInt(2, quantity);
		statement.setInt(3, status);
		statement.setString(4, info.getTransId());
		statement.execute();
		conn.close();
	}
	
	public Map<String, String> getStockCodeNamePair() throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from all_stock t order by t.code";
		ResultSet rs = statement.executeQuery(sql);  
		while (rs.next()) {
			String code = rs.getString("code");
			String name = rs.getString("name");
			result.put(code, name);
		}  
		rs.close();
		conn.close();
		return result;
	}
	
	public void updateMonitorStatus(String transId, Integer isMonitor) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("update my_stock t set t.is_monitor = ? where t.transaction_id = ?");
		statement.setInt(1, isMonitor);
		statement.setString(2, transId);
		statement.execute();
		conn.close();
	}
	
	public void addAlert(String stockCode, Integer alertType, String message) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("insert into monitor_alert values (?,?,?,?,?,?)");
		String alertId = UUID.randomUUID().toString();
		statement.setString(1, alertId);
		statement.setInt(2, StockConstants.ALERT_TYPE_STOCK_DROP);
		statement.setString(3, stockCode);
		statement.setString(4, message);
		statement.setInt(5, StockConstants.ALERT_STATUS_TO_NOTICE);
		statement.setTimestamp(6, Timestamp.valueOf(CommonUtils.getDateString(new Date())));
		statement.execute();
		conn.close();
	}
	
	public List<AlertVO> readAlert() throws Exception {
		List<AlertVO> result = new ArrayList<AlertVO>();
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from monitor_alert t where t.status = " + StockConstants.ALERT_STATUS_TO_NOTICE + " order by t.alert_time desc";
		ResultSet rs = statement.executeQuery(sql);  
		while (rs.next()) {
			AlertVO alertVO = new AlertVO();
			String alertId = rs.getString("alert_id");
			Integer alertType = rs.getInt("alert_type");
			String stockCode = rs.getString("stock_code");
			String message = rs.getString("message");
			Integer status = rs.getInt("status");
			Date alertTime = rs.getDate("alert_time");
			alertVO.setAlertId(alertId);
			alertVO.setAlertType(alertType);
			alertVO.setStockCode(stockCode);
			alertVO.setMessage(message);
			result.add(alertVO);
		}  
		rs.close();  
		conn.close();
		return result;
	}
	
	public void updateAlertStatus(String alertId, Integer status) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("update monitor_alert t set t.status = ? where t.alert_id = ?");
		statement.setInt(1, status);
		statement.setString(2, alertId);
		statement.execute();
		conn.close();
	}
	
	public String addRuleHistory(String ruleId, String historyId, Integer result, Long timeCost, Integer stockNum) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("insert into rule_run_history values (?,?,?,?,?,?)");
		statement.setString(1, historyId);
		statement.setString(2, ruleId);
		statement.setTimestamp(3, Timestamp.valueOf(CommonUtils.getDateString(new Date())));
		statement.setInt(4, result);
		statement.setLong(5, timeCost);
		statement.setInt(6, stockNum);
		statement.execute();
		conn.close();
		return historyId;
	}
	
	public void addRuleResult(String historyId, String stockCode) throws Exception {
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("insert into rule_result values (?,?,?,?,?,?)");
		String id = UUID.randomUUID().toString();
		statement.setString(1, id);
		statement.setString(2, historyId);
		statement.setString(3, stockCode);
		statement.setDouble(4, 0.0);
		statement.setDouble(5, 0.0);
		statement.setDouble(6, 0.0);
		statement.execute();
		conn.close();
	}
	
	public List<Stock> getRuleResultByHistoryId(String historyId) throws Exception {
		List<Stock> result = new ArrayList<Stock>();
		Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement("select * from rule_result t where t.history_id = ? order by stock_code");
		statement.setString(1, historyId);
		ResultSet rs = statement.executeQuery();  
		while (rs.next()) {
			Stock stock = new Stock();
			stock.setCode(rs.getString("stock_code"));
			result.add(stock);
		}  
		rs.close();  
		conn.close();
		return result;
	}
	
	public void updateRuleResultTrend(String historyId, String stockCode, Integer day, Double profit) throws Exception {
		Connection conn = getConnection();
		String dayCol = "";
		if (day.equals(StockConstants.FIRST_DAY_RESULT)) {
			dayCol = "first_day_trend";
		} else if (day.equals(StockConstants.SECOND_DAY_RESULT)) {
			dayCol = "second_day_trend";
		} else if (day.equals(StockConstants.THIRD_DAY_RESULT)) {
			dayCol = "third_day_trend";
		} else {
			return;
		}
		String sql = "update rule_result t set t." + dayCol + " = ? where t.history_id = ? and t.stock_code = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, profit);
		statement.setString(2, historyId);
		statement.setString(3, stockCode);
		statement.execute();
		conn.close();
	}
	
	public List<RuleItemVO> getRuleItemByType(Integer type) throws Exception {
		List<RuleItemVO> result = new ArrayList<RuleItemVO>();
		Connection conn = getConnection();
		String sql = "select * from rule_item t where t.TYPE = ? order by t.ID";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, type);
		ResultSet rs = statement.executeQuery();  
		while (rs.next()) {
			RuleItemVO ruleItem = new RuleItemVO();
			ruleItem.setId(rs.getString("id"));
			ruleItem.setName(rs.getString("name"));
			ruleItem.setType(rs.getInt("type"));
			ruleItem.setImplClz(rs.getString("impl_class"));
			ruleItem.setDesp(rs.getString("description"));
			result.add(ruleItem);
		}  
		return result;
	}
	
	public void saveOrUpdateRuleItem(RuleItemVO ruleItem) throws Exception {
		if (ruleItem != null && ruleItem.getId() != null 
				&& !"".equals(ruleItem.getId())) {
			Connection conn = getConnection();
			String sql = "select count(*) from rule_item t where t.id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ruleItem.getId());
			ResultSet rs = statement.executeQuery();
			if (rs.next() && rs.getInt(0) > 0) {
				String update = "update rule_item t set t.name = ?, t.impl_class = ?, t.description = ? where t.id = ?";
				statement = conn.prepareStatement(update);
				statement.setString(1, ruleItem.getName());
				statement.setString(2, ruleItem.getImplClz());
				statement.setString(3, ruleItem.getDesp());
				statement.setString(4, ruleItem.getId());
				statement.execute();
			} else {
				String insert = "insert into rule_item t values (?, ?, ?, ?)";
				statement = conn.prepareStatement(insert);
				statement.setString(1, ruleItem.getId());
				statement.setString(2, ruleItem.getName());
				statement.setString(3, ruleItem.getImplClz());
				statement.setString(4, ruleItem.getDesp());
				statement.execute();
			}
		} else {
			throw new Exception("Rule item is null");
		}
	}
	
	public void deleteRuleItem(RuleItemVO ruleItem) throws Exception {
		if (ruleItem != null && ruleItem.getId() != null 
				&& !"".equals(ruleItem.getId())) {
			Connection conn = getConnection();
			String sql = "delete from rule_item t where t.id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ruleItem.getId());
			statement.execute();
		}
	}
}
 