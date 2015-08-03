package stock.db;

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

import stock.db.connect.DBConnector;
import stock.util.StockConstants;
import stock.util.StockUtils;
import stock.vo.MyStockInfo;
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
		String sql = "select * from stock order by code";
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
		stock.setCode(code);
		stock.setTransId(transId);
		stock.setBuyingPrice(price);
		stock.setQuantity(quantity);
		stock.setBuyingTime(date);
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
		PreparedStatement statement = conn.prepareStatement("insert into my_stock values (?,?,?,?,?,?)");
		statement.setString(1, StockUtils.createTransactionId(info.getCode()));
		statement.setString(2, info.getCode());
		statement.setDouble(3, info.getBuyingPrice());
		statement.setInt(4, info.getQuantity());
		statement.setTimestamp(5, Timestamp.valueOf(StockUtils.getDateString(info.getBuyingTime())));
		statement.setInt(6, StockConstants.MY_STOCK_STATUS_IN);
		statement.execute();
	}
	
	public void updateMyStock(MyStockInfo info) throws Exception {
	}
	
	public Map<String, String> getStockCodeNamePair() throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from stock t order by t.code";
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
	
}
 