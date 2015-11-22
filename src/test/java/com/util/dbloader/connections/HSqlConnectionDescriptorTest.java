package com.util.dbloader.connections;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.util.dbloader.DbTestFixture;

public class HSqlConnectionDescriptorTest extends DbTestFixture {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testHSqlConnectionDescriptor() throws ClassNotFoundException, SQLException {
		String url = String.format("jdbc:hsqldb:file:%s/%s;shutdown=true",
				super.classResourcesDir.getAbsolutePath(), "HSqlCDT");
		HSqlConnectionDescriptor connector = new HSqlConnectionDescriptor(url,"sa","");
		Connection c = connector.createConnection();
		Assert.assertNotNull(c);
		Assert.assertFalse(c.isClosed());
		c.close();
		Assert.assertTrue(c.isClosed());
		super.cleanupAndDeleteDb("HSqlCDT");
	}

}
