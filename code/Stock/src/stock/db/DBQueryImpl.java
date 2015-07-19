package stock.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import stock.db.connect.DBConnector;
import stock.vo.Stock;

public class DBQueryImpl implements DBQuery {
	
	private DBConnector connector;

	public DBConnector getConn() {
		return connector;
	}

	public void setConn(DBConnector connector) {
		this.connector = connector;
	}

	@Override
	public List<Stock> getStockList() throws Exception {
		// TODO Auto-generated method stub
		List<Stock> result = new ArrayList<Stock>();
		Connection conn = null;
		if (connector != null) {
			conn = this.connector.getConnection();
		}
		if (conn != null && !conn.isClosed()) {
			Statement statement = conn.createStatement();
			String sql = "select * from stock";
			ResultSet rs = statement.executeQuery(sql);  
			while (rs.next()) {
				Stock stock = new Stock();
				stock.setCode(rs.getString("code"));
				stock.setName(rs.getString("name"));
			}  
			rs.close();  
			conn.close();
		}
		return result;
	}
	
}
