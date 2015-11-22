package com.util.dbloader;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DbTestFixtureTest extends DbTestFixture {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testCreateDb() throws ClassNotFoundException, SQLException {
		Connection conn = super.createDb("testCreateDb");
		Assert.assertNotNull(conn);
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.tmp").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.script").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.log").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.properties").exists());
	}

	@Test
	public void testCreateDb_AndClose() throws ClassNotFoundException, SQLException {
		Connection conn = super.createDb("testCreateDb");
		Assert.assertNotNull(conn);
		conn.close();
		Assert.assertFalse(new File(super.classResourcesDir, "testCreateDb.tmp").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.script").exists());
		Assert.assertFalse(new File(super.classResourcesDir, "testCreateDb.log").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.properties").exists());
	}

	@Test
	public void testCreateDb_Drop() throws ClassNotFoundException, SQLException {
		Connection conn = super.createDb("testCreateDb");
		Assert.assertNotNull(conn);
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.tmp").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.script").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.log").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.properties").exists());
		super.cleanupAndDeleteDb("testCreateDb");
		Assert.assertFalse(new File(super.classResourcesDir, "testCreateDb.tmp").exists());
		Assert.assertFalse(new File(super.classResourcesDir, "testCreateDb.script").exists());
		Assert.assertFalse(new File(super.classResourcesDir, "testCreateDb.log").exists());
		Assert.assertFalse(new File(super.classResourcesDir, "testCreateDb.properties").exists());
	}

	@Test
	public void testCreateDb_CreateTable() throws ClassNotFoundException, SQLException {
		Connection conn = super.createDb("testCreateDb");
		Assert.assertNotNull(conn);
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.tmp").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.script").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.log").exists());
		Assert.assertTrue(new File(super.classResourcesDir, "testCreateDb.properties").exists());
		Statement st = conn.createStatement();
		st.executeUpdate("CREATE TABLE FOO (ID INTEGER, NAME VARCHAR(30))");
		st.close();
		st = conn.createStatement();
		st.executeUpdate("INSERT INTO FOO (ID, NAME) VALUES (1, 'James')");
		st.close();
		st = conn.createStatement();
		st.executeUpdate("INSERT INTO FOO (ID, NAME) VALUES (2, 'Barbara')");
		st.close();
		st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM FOO");
		while (rs.next()) {
			System.out.printf("%d %s%n", rs.getInt(1), rs.getString(2));
		}
	}

}
