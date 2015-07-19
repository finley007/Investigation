package stock.db.connect;

import java.sql.Connection;

/**
 * 
 * @author liuli
 * Interface for DB connector
 * 
 */

public interface DBConnector {
	
	public Connection getConnection() throws Exception;

}
