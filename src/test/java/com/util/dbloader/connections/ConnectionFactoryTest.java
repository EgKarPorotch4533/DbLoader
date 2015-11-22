package com.util.dbloader.connections;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.util.dbloader.TestFixture;
import com.util.dbloader.configurations.Configurator;

public class ConnectionFactoryTest extends TestFixture {

	private static final String CONFIGURATION = "configuration.yml";

	@Before
	public void setUp() throws Exception {
		super.setUp();
		String urlSrc = String.format("jdbc:hsqldb:file:%s/%s;shutdown=true",
				super.classResourcesDir.getAbsolutePath(), "tstSrc");
		String urlDst = String.format("jdbc:oracle:thin:@bla.bla.bla");
		StringBuilder sb = new StringBuilder();
		sb.append("mappings:").append('\n');
		sb.append("    -").append('\n');
		sb.append("        source:").append('\n');
		sb.append("            tableName: table").append('\n');
		sb.append("            schemaName: schema").append('\n');
		sb.append("            connection:").append('\n');
		sb.append("                url: " + urlSrc).append('\n');
		sb.append("                user: sa").append('\n');
		sb.append("                pass: ").append('\n');
		sb.append("                vendor: HSQL").append('\n');
		sb.append("        destination:").append('\n');
		sb.append("            tableName: table").append('\n');
		sb.append("            schemaName: schema").append('\n');
		sb.append("            connection:").append('\n');
		sb.append("                url: " + urlDst).append('\n');
		sb.append("                user: sa").append('\n');
		sb.append("                pass: ").append('\n');
		sb.append("                vendor: ORACLE").append('\n');
		super.createResource(CONFIGURATION, sb.toString());
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetConnectionDescriptor() throws Exception {
		Configurator configurator = new Configurator(super.getResource(CONFIGURATION));
		ConnectionDescriptor srcDescriptor = ConnectionFactory
				.getConnectionDescriptor(
						configurator.getConfiguration().getMappings().get(0).getSource().getConnection());
		Assert.assertNotNull(srcDescriptor);
		ConnectionDescriptor dstDescriptor = ConnectionFactory
				.getConnectionDescriptor(
						configurator.getConfiguration().getMappings().get(0).getDestination().getConnection());
		Assert.assertNotNull(dstDescriptor);
		Assert.assertTrue(srcDescriptor instanceof HSqlConnectionDescriptor);
		Assert.assertTrue(dstDescriptor instanceof OracleConnectionDescriptor);
	}

}
