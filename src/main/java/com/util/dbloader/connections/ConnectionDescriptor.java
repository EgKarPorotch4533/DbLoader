package com.util.dbloader.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author ekorotchenko
 *
 */
public class ConnectionDescriptor {

	static final String POSTGRES_DRIVER_CLASS_NAME = "org.postgresql.Driver";
	static final String ORACLE_DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";

	private final String url;
	private final String user;
	private final String pass;
	
	public ConnectionDescriptor(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}
	
	public String getDriverClassName() {
		return null;
	}
	
	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(
				url, user, pass);
	}
}
