package com.util.dbloader.configurations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.dbloader.TestFixture;

public class ConfiguratorTest extends TestFixture {
	
	private static final String CONFIGURATION = "configuration.yml";

	Configurator configurator;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		StringBuilder sb = new StringBuilder();
		sb.append("mappings:").append('\n');
		sb.append("    -").append('\n');
		sb.append("        source:").append('\n');
		sb.append("            tableName: table").append('\n');
		sb.append("            schemaName: schema").append('\n');
		sb.append("            connection:").append('\n');
		sb.append("                url: jdbc.oracle.thin@bla.bla").append('\n');
		sb.append("                user: me").append('\n');
		sb.append("                pass: secret").append('\n');
		sb.append("                vendor: ORACLE").append('\n');
		sb.append("        destination:").append('\n');
		sb.append("            tableName: table").append('\n');
		sb.append("            schemaName: schema").append('\n');
		sb.append("            connection:").append('\n');
		sb.append("                url: jdbc.oracle.thin@bla.bla").append('\n');
		sb.append("                user: me").append('\n');
		sb.append("                pass: secret").append('\n');
		sb.append("                vendor: ORACLE").append('\n');
		super.createResource(CONFIGURATION, sb.toString());
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testConfigurator() throws FileNotFoundException {
		configurator = new Configurator(super.getResource(CONFIGURATION));
		assertNotNull(configurator);
		assertNotNull(configurator.getConfiguration());
		assertNotNull(configurator.getConfiguration().getMappings());
		for (Mapping mapping : configurator.getConfiguration().getMappings()) {
			assertNotNull(mapping);
			assertNotNull(mapping.getSource());
			assertNotNull(mapping.getSource().getTableName());
			assertEquals("table", mapping.getSource().getTableName());
			assertNotNull(mapping.getDestination());
		}
	}

}
