package com.util.dbloader;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

public class DbTestFixture extends TestFixture {

	public static final String HSQL_DRIVER_NAME = "org.hsqldb.jdbcDriver";
	
	Connection connection;

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	public Connection createDb(String dbFileName) throws SQLException, ClassNotFoundException {
		Class.forName(HSQL_DRIVER_NAME);
		String url = String.format("jdbc:hsqldb:file:%s/%s;shutdown=true",
				super.classResourcesDir.getAbsolutePath(), dbFileName);
		return DriverManager.getConnection(url, "sa", "");
	}
	
	public void closeSilent(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {}
		}
	}
	
	public void cleanupDb(String dbFileName) {
		File dbFileToCleanup = new File(super.classResourcesDir, dbFileName);
		if (dbFileToCleanup.exists()) {
			dbFileToCleanup.delete();
		}
	}
}
