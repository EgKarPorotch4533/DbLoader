package com.util.dbloader.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.util.dbloader.TestFixture;

public class ConnectionTest extends TestFixture {
	
	
	private static final String CONNECTION = "connection.yml";
	
	Yaml yaml = null;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		yaml = new Yaml();
		super.createResource(CONNECTION, "url: jdbc.oracle.thin@bla.bla\nuser: me\npass: secret\nvendor: ORACLE");
		
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testReadConnection() throws FileNotFoundException {
		InputStream in = null;
		com.util.dbloader.configurations.Connection connection = yaml
				.loadAs(in = new FileInputStream(super.getResource(CONNECTION)), 
						com.util.dbloader.configurations.Connection.class);
		super.toClose(in);
		Assert.assertNotNull(connection);
		Assert.assertNotNull(connection.getUser());
		Assert.assertEquals("me", connection.getUser());
		Assert.assertNotNull(connection.getUrl());
		Assert.assertEquals("jdbc.oracle.thin@bla.bla", connection.getUrl());
		Assert.assertNotNull(connection.getPass());
		Assert.assertEquals("secret", connection.getPass());
		Assert.assertNotNull(connection.getVendor());
		Assert.assertTrue(connection.getVendor() == Connection.Vendors.ORACLE);
	}
	
}
