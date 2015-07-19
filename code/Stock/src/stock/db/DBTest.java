package stock.db;

import java.sql.*;   

public class DBTest {
	
	public static void main(String[] args){  
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/stock";
		String user = "root";
		String password = "";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
				Statement statement = conn.createStatement();
				String sql = "select * from stock";
				ResultSet rs = statement.executeQuery(sql);  
				String name = null;  
				while(rs.next()) {  
					name = rs.getString("code");
					System.out.println(name);  
				}  
			rs.close();  
			conn.close();
			}
		} catch(ClassNotFoundException e) {   
			System.out.println("Sorry,can`t find the Driver!");   
			e.printStackTrace();   
		} catch(SQLException e) {   
			e.printStackTrace();   
		} catch(Exception e) {   
			e.printStackTrace();   
		}   
	}   


}
