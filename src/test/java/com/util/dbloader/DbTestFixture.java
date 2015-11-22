package com.util.dbloader;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
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
		return createDb(dbFileName, true);
	}
	
	public Connection createDb(String dbFileName, boolean shutdown) throws SQLException, ClassNotFoundException {
		Class.forName(HSQL_DRIVER_NAME);
		String url = String.format("jdbc:hsqldb:file:%s/%s;shutdown=" + shutdown,
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
	
	public void cleanupAndDeleteDb(String dbFileName) {
		File dbFolder = new File(super.classResourcesDir, dbFileName + ".tmp");
		File dbLogFile = new File(super.classResourcesDir, dbFileName + ".log");
		File dbPropFile = new File(super.classResourcesDir, dbFileName + ".properties");
		File dbScriptFile = new File(super.classResourcesDir, dbFileName + ".script");
		if (dbLogFile.exists()) {
			dbLogFile.delete();
		}
		if (dbPropFile.exists()) {
			dbPropFile.delete();
		}
		if (dbScriptFile.exists()) {
			dbScriptFile.delete();
		}
		if (dbFolder.exists()) {
			try {
				FileUtils.forceDelete(dbFolder);
			} catch (IOException e) {}
		}
	}
}
