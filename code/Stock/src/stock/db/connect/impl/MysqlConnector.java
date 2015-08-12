package stock.db.connect.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import stock.db.connect.DBConnector;

public class MysqlConnector implements DBConnector {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/stock";
	private String user = "root";
	private String password = "";

	public Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}

}
