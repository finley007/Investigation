package stock.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import stock.db.connect.DBConnector;
import stock.vo.Stock;
import stock.vo.StockInfo;

public class DBQueryImpl implements DBQuery {
	
	private DBConnector connector;

	public DBConnector getConn() {
		return connector;
	}

	public void setConn(DBConnector connector) {
		this.connector = connector;
	}

	public List<Stock> getStockList() throws Exception {
		// TODO Auto-generated method stub
		List<Stock> result = new ArrayList<Stock>();
		Connection conn = null;
		if (connector != null) {
			conn = this.connector.getConnection();
		}
		if (conn != null && !conn.isClosed()) {
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
		}
		return result;
	}
	
	public List<StockInfo> getMyStock() throws Exception {
		List<StockInfo> result = new ArrayList<StockInfo>();
		Connection conn = null;
		if (connector != null) {
			conn = this.connector.getConnection();
		}
		if (conn != null && !conn.isClosed()) {
			Statement statement = conn.createStatement();
			String sql = "select * from my_stock t where t.status = 1 order by t.stock_id";
			ResultSet rs = statement.executeQuery(sql);  
			while (rs.next()) {
				String code = rs.getString("stock_id");
				String name = rs.getString("stock_id");
				StockInfo stock = new StockInfo(new Stock(code, name));
				result.add(stock);
			}  
			rs.close();  
			conn.close();
		}
		return result;
	}
	
}
 