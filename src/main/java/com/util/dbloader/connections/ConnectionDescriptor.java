package com.util.dbloader.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author ekorotchenko
 *
 */
public abstract class ConnectionDescriptor {

	static final String POSTGRES_DRIVER_CLASS_NAME = "org.postgresql.Driver";
	static final String ORACLE_DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	static final String HSQL_DRIVER_CLASS_NAME = "org.hsqldb.jdbcDriver";

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
	
	public abstract String getDriverClassName();
	
	public Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName(getDriverClassName());
		return DriverManager.getConnection(
				url, user, pass);
	}
}
