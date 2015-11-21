package com.util.dbloader.configurations;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.util.dbloader.TestFixture;

public class TestFixtureTest extends TestFixture {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void test() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertNotNull(classResourcesDir);
		Assert.assertEquals("configurations", classResourcesDir.getName());
		Assert.assertEquals("dbloader", classResourcesDir.getParentFile().getName());
		Assert.assertTrue(classResourcesDir.exists());
	}
}
