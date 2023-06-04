package utils;

import java.sql.*;

public class DB {
	
	private Connection connection = null;
	
	public DB() throws Exception {
		
		// WITHOUT POOL
		String user = "mysql";
		String password="prac";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/twitter_sports?serverTimezone=UTC&user="+user+"&password="+password);

	}
	
	//execute queries
	
	public PreparedStatement prepareStatement(String query) throws SQLException{
		return connection.prepareStatement(query);
	}
	
	public void disconnectBD() throws SQLException{
		connection.close();
	}
}