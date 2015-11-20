package com.util.dbloader;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.RecordCache;
import com.util.dbloader.samples.PartitionCollector;
import com.util.dbloader.workers.PartitionSelectWorker;

public class Loader {
	
	private static final int NUM_OF_READERS = 4;
	private static final int NUM_OF_WRITERS = 4;
	private static final int MIN_INITIAL_ITEMS = 10;
	
	private final ConnectionDescriptor sourceDescriptor;
	private final ConnectionDescriptor destDescriptor;
	private final String tableName;
	private final String sourceSchema;
	private final String destSchema;
	
	public Loader(ConnectionDescriptor sourceDescriptor, ConnectionDescriptor destDescriptor,
			String tableName, String sourceSchema, String destSchema) {
		this.sourceDescriptor = sourceDescriptor;
		this.destDescriptor = destDescriptor;
		this.tableName = tableName;
		this.sourceSchema = sourceSchema;
		this.destSchema = destSchema;
	}
	
	public void start() throws SQLException, InterruptedException {
		List<String> partitions = getSourcePartitions();
		if (partitions.size() > 0) {
			startOnPartitions(partitions);
		} else {
			startOnEntire();
		}
	}
	
	private void startOnPartitions(List<String> partitions) throws SQLException, InterruptedException {
		LinkedBlockingQueue<String> partitionQueue = createPartitionQueue(partitions);
		LinkedBlockingQueue<RecordCache> cacheQueue = new LinkedBlockingQueue<RecordCache>();
		ExecutorService service = Executors.newFixedThreadPool(NUM_OF_READERS + NUM_OF_WRITERS);
		for (int i = 0; i < NUM_OF_READERS; i++) {
			service.execute(new PartitionSelectWorker(sourceDescriptor, tableName, sourceSchema, partitionQueue, cacheQueue));
		}
		// wait for readers to collect initial data
		while (partitionQueue.size() > 0 && cacheQueue.size() < MIN_INITIAL_ITEMS) {
			Thread.sleep(2000);
		}
		// for short case
		if (partitionQueue.size() == 0) {
			
		}
	}
	
	private void startOnEntire() {
		LinkedBlockingQueue<RecordCache> cacheQueue = new LinkedBlockingQueue<RecordCache>();
	}
	
	private LinkedBlockingQueue<String> createPartitionQueue(List<String> partitions) throws SQLException {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		for (String partition : partitions) {
			queue.add(partition);
		}
		return queue;
	}
	
	private List<String> getSourcePartitions() throws SQLException {
		return new PartitionCollector(sourceDescriptor)
			.list(tableName, sourceSchema);
	}
	
}
