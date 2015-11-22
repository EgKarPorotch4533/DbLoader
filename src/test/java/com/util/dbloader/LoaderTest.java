package com.util.dbloader;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.util.dbloader.configurations.Configurator;

public class LoaderTest extends DbTestFixture {

	private static final String CONFIGURATION = "configuration.yml";

	Configurator configurator;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		createConfiguration();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testLoaderMapping_Short() {
		//fail("Not yet implemented");
	}

	@Test
	public void testLoaderMapping_BigIntegration() {
		//fail("Not yet implemented");
	}
	
	private void createConfiguration() throws IOException {
		String urlSrc = String.format("jdbc:hsqldb:file:%s/%s;shutdown=true",
				super.classResourcesDir.getAbsolutePath(), "tstSrc");
		String urlDst = String.format("jdbc:hsqldb:file:%s/%s;shutdown=true",
				super.classResourcesDir.getAbsolutePath(), "tstDsc");
		StringBuilder sb = new StringBuilder();
		sb.append("mappings:").append('\n');
		sb.append("    -").append('\n');
		sb.append("        source:").append('\n');
		sb.append("            tableName: table1").append('\n');
		sb.append("            schemaName: schema2").append('\n');
		sb.append("            connection:").append('\n');
		sb.append("                url: " + urlSrc).append('\n');
		sb.append("                user: sa").append('\n');
		sb.append("                pass: ").append('\n');
		sb.append("                vendor: HSQL").append('\n');
		sb.append("        destination:").append('\n');
		sb.append("            tableName: table2").append('\n');
		sb.append("            schemaName: schema2").append('\n');
		sb.append("            connection:").append('\n');
		sb.append("                url: " + urlDst).append('\n');
		sb.append("                user: sa").append('\n');
		sb.append("                pass: ").append('\n');
		sb.append("                vendor: ORACLE").append('\n');
		super.createResource(CONFIGURATION, sb.toString());
	}
	
	private void createSourceTable() {
		
	}

}
