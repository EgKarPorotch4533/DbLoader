package com.util.dbloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;

public class TestFixture {
	
	protected static final File TESTS_ROOT = new File("src/test/resources");
	
	protected File classResourcesDir = null;
	protected Map<String, File> resourcesMap;
	protected List<Closeable> streamsToClose;
	long clearTime;

	@Before
	public void setUp() throws Exception {
		classResourcesDir = new File(TESTS_ROOT, this.getClass().getPackage().getName().replaceAll("\\.", "/"));
		if (!classResourcesDir.exists()) {
			classResourcesDir.mkdirs();
		}
		resourcesMap = new HashMap<String, File>();
		streamsToClose = new LinkedList<Closeable>();
		clearTime = System.currentTimeMillis();
	}
	
	@After
	public void tearDown() throws Exception {
		clearTime = System.currentTimeMillis() - clearTime;
		dropResources();
		System.out.println();
		System.out.printf("%nellapsed: %d (%s)", clearTime, TimeUtil.toHumanPeriod(clearTime));
	}
	
	private void dropResources() {
		for (Entry<String, File> entry : resourcesMap.entrySet()) {
			if (entry.getValue().exists()) {
				entry.getValue().delete();
			}
		}
		for (Closeable stream : streamsToClose) {
			drop(stream);
		}
	}
	
	private void drop(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {}
		}
	} 
	
	public void toClose(Closeable stream) {
		streamsToClose.add(stream);
	}
	
	public void createResource(String name, String content) throws IOException {
		File resource = new File(classResourcesDir, name);
		if (resource.exists()) {
			resource.delete();
		}
		if (!resource.getParentFile().exists()) {
			resource.getParentFile().mkdirs();
		}
		resource.createNewFile();
		Closeable c1 = null, c2 = null;
		((PrintStream) (c1 = new PrintStream((OutputStream) (c2 = new FileOutputStream(resource))))).print(content);
		drop(c1);
		drop(c2);
		resourcesMap.put(name, resource);
	}
	
	public File getResource(String name) {
		return resourcesMap.get(name);
	}
}
