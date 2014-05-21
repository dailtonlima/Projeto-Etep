package br.edu.etep.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnManager {
	
	protected static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static String url = "jdbc:sqlserver://PC-DAILTONLIMA:1433;databaseName=Studio";
	protected static String userName= "studiosysuser";
	protected static String password= "xE19WIAH";

	private static Connection connectionPool = null;

	public DBConnManager() {
		
	}
        
	private static void initialize() {
		// Here we can initialize all the information that we need
		initializeConnectionPool();
	}

	private static void initializeConnectionPool() {
		
		if (!checkIfConnectionPoolIsFull()) {
			// Adding new connection instance until the pool is full
			connectionPool =  createNewConnectionForPool();
			
		}
	}

	private static synchronized boolean checkIfConnectionPoolIsFull() {

		// Check if the pool 
		if (connectionPool == null) {
			return false;
		}

		return true;
	}

	// Creating a connection
	private static Connection createNewConnectionForPool() {

		Connection connection = null;

		try {

			Class.forName(driver);
			connection = DriverManager.getConnection(url, userName, password);
//			System.out.println("Connection: " + connection + " " + connection.getCatalog());

		} catch (SQLException sqle) {

			System.err.println("SQLException: " + sqle);
			return null;

		} catch (ClassNotFoundException cnfe) {

			System.err.println("ClassNotFoundException: " + cnfe);
			return null;

		}

		return connection;
	}

	public static synchronized Connection getConnectionFromPool() {
                initialize();
		return connectionPool;
	}

}
