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

public class EndPointTest extends TestFixture {
	
	private static final String ENDPOINT = "endpoint.yml";
	
	Yaml yaml = null;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		yaml = new Yaml();
		StringBuilder sb = new StringBuilder();
		sb.append("tableName: table").append('\n');
		sb.append("schemaName: schema").append('\n');
		sb.append("connection:").append('\n');
		sb.append("    url: jdbc.oracle.thin@bla.bla").append('\n');
		sb.append("    user: me").append('\n');
		sb.append("    pass: secret").append('\n');
		sb.append("    vendor: ORACLE").append('\n');
		super.createResource(ENDPOINT, sb.toString());
		
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() throws FileNotFoundException {
		InputStream in = null;
		EndPoint endpoint = yaml
				.loadAs(in = new FileInputStream(super.getResource(ENDPOINT)), EndPoint.class);
		super.toClose(in);
		Assert.assertNotNull(endpoint);
		Assert.assertNotNull(endpoint.getTableName());
		Assert.assertEquals("table", endpoint.getTableName());
		Connection connection = endpoint.getConnection();
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
