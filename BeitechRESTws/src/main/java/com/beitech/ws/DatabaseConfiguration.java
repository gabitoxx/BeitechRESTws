package com.beitech.ws;

import java.sql.*;


public class DatabaseConfiguration {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		
		/**
		 * Create the MySQL database connection
		 *  pending: next time use *.properties to set these values
		 */
		//String myDriver = "org.gjt.mm.mysql.Driver";
		String sDriver  = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost/";
		String user = "root"; 
		String pass = "l4nuz4D4T4845E";
		
		try {
			Class.forName(sDriver);
			
			connection = DriverManager.getConnection(myUrl, user, pass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
		return connection;
	}
	
	
}