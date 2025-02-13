package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IConnectionString {
	private static String CONNECTION_URL = "jdbc:mysql://localhost/Competitors";
	private static String USERNAME = "root";
	private static String PASS= "";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(CONNECTION_URL,USERNAME,PASS);
	}
	
}
