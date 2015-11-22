package com.util.dbloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.util.dbloader.configurations.Configurator;
import com.util.dbloader.configurations.Mapping;
import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.connections.ConnectionFactory;
import com.util.dbloader.model.Metadata;
import com.util.dbloader.model.RecordCache;
import com.util.dbloader.queries.SourceReader;
import com.util.dbloader.samples.PartitionCollector;
import com.util.dbloader.workers.BulkInsertWorker;
import com.util.dbloader.workers.PartitionSelectWorker;
import com.util.dbloader.workers.SelectWorker;

public class Loader {
	
	private static final int NUM_OF_READERS = 4;
	private static final int NUM_OF_WRITERS = 4;
	private static final int MIN_INITIAL_ITEMS = 10;
	private static final int BULK_INSERT_SIZE = 50;
	
	private static final long MONITOR_SLEEP_MS = 1000 * 10L;
	
	private final ConnectionDescriptor sourceDescriptor;
	private final ConnectionDescriptor destDescriptor;
	private final String sourceTable;
	private final String destTable;
	private final String sourceSchema;
	private final String destSchema;
	
	public static void main(String ... args) throws SQLException, InterruptedException, Exception {
		if (args.length != 1) {
			System.err.println("path to yaml config file as first argument");
			return;
		}
		try {
			Configurator configurator = new Configurator(new File(args[0]));
			for (Mapping mapping : configurator.getConfiguration().getMappings()) {
				new Loader(mapping).start();
			}
		} catch (FileNotFoundException e) {
			System.err.printf("file not found %s%n", new File(args[0]).getAbsolutePath());
			return;
		}
	}
	
	public Loader(Mapping mapping) throws Exception {
		this(
				ConnectionFactory.getConnectionDescriptor(mapping.getSource().getConnection()), 
				ConnectionFactory.getConnectionDescriptor(mapping.getDestination().getConnection()), 
				mapping.getSource().getTableName(),
				mapping.getDestination().getTableName(),
				mapping.getSource().getSchemaName(), 
				mapping.getDestination().getSchemaName());
	}
	
	public Loader(ConnectionDescriptor sourceDescriptor, ConnectionDescriptor destDescriptor,
			String sourceTable, String destTable, String sourceSchema, String destSchema) {
		this.sourceDescriptor = sourceDescriptor;
		this.destDescriptor = destDescriptor;
		this.sourceTable = sourceTable;
		this.destTable = destTable;
		this.sourceSchema = sourceSchema;
		this.destSchema = destSchema;
	}
	
	public void start() throws SQLException, InterruptedException, ClassNotFoundException {
		List<String> partitions = getSourcePartitions();
		if (partitions.size() > 0) {
			startOnPartitions(partitions);
		} else {
			startOnEntire();
		}
	}
	
	private void startOnPartitions(List<String> partitions) throws SQLException, InterruptedException, ClassNotFoundException {
		LinkedBlockingQueue<String> partitionQueue = createPartitionQueue(partitions);
		LinkedBlockingQueue<RecordCache> cacheQueue = new LinkedBlockingQueue<RecordCache>();
		Metadata md = new SourceReader(sourceDescriptor).fetchMetafata(sourceTable, sourceSchema);
		ExecutorService service = Executors.newFixedThreadPool(NUM_OF_READERS + NUM_OF_WRITERS);
		// create readers
		for (int i = 0; i < NUM_OF_READERS; i++) {
			service.execute(new PartitionSelectWorker(sourceDescriptor, sourceTable, sourceSchema, partitionQueue, cacheQueue));
		}
		// wait for readers to collect initial data
		while (partitionQueue.size() > 0 && cacheQueue.size() < MIN_INITIAL_ITEMS) {
			Thread.sleep(2000);
		}
		// for no data case
		if (partitionQueue.size() == 0 && cacheQueue.size() == 0) {
			return;
		}
		// create writers
		for (int k = 0; k < NUM_OF_WRITERS; k++) {
			service.execute(new BulkInsertWorker(destDescriptor, md, destTable, destSchema, cacheQueue, BULK_INSERT_SIZE));
		}
		// wait for writers to start
		Thread.sleep(3000);
		
		// main cycle
		while (!(partitionQueue.isEmpty() && cacheQueue.isEmpty())) {
			System.out.printf("[main] source queue: %d, dest queue: %d%n", partitionQueue.size(), cacheQueue.size());
			Thread.sleep(MONITOR_SLEEP_MS);
		}

		// wait for some time before shutdown
		Thread.sleep(3000);
		
		service.shutdown();

		service.awaitTermination(10, TimeUnit.SECONDS);
	}
	
	private void startOnEntire() throws InterruptedException, ClassNotFoundException, SQLException {
		LinkedBlockingQueue<RecordCache> cacheQueue = new LinkedBlockingQueue<RecordCache>();
		Metadata md = new SourceReader(sourceDescriptor).fetchMetafata(sourceTable, sourceSchema);
		ExecutorService service = Executors.newFixedThreadPool(NUM_OF_READERS + NUM_OF_WRITERS);
		// create readers
		for (int i = 0; i < 1; i++) {
			service.execute(new SelectWorker(sourceDescriptor, sourceTable, sourceSchema, cacheQueue));
		}
		// wait for readers to collect initial data
		while (cacheQueue.size() < MIN_INITIAL_ITEMS) {
			Thread.sleep(2000);
		}
		// create writers
		for (int k = 0; k < NUM_OF_WRITERS; k++) {
			service.execute(new BulkInsertWorker(destDescriptor, md, destTable, destSchema, cacheQueue, BULK_INSERT_SIZE));
		}
		// wait for writers to start
		Thread.sleep(3000);
		
		// main cycle
		while (!cacheQueue.isEmpty()) {
			System.out.printf("[main] source queue: %d, dest queue: %d%n", cacheQueue.size());
			Thread.sleep(MONITOR_SLEEP_MS);
		}

		// wait for some time before shutdown
		Thread.sleep(3000);
		
		service.shutdown();

		service.awaitTermination(10, TimeUnit.SECONDS);
	}
	
	private LinkedBlockingQueue<String> createPartitionQueue(List<String> partitions) throws SQLException {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		for (String partition : partitions) {
			queue.add(partition);
		}
		return queue;
	}
	
	private List<String> getSourcePartitions() throws SQLException, ClassNotFoundException {
		return new PartitionCollector(sourceDescriptor)
			.list(sourceTable, sourceSchema);
	}
	
}
