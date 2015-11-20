package com.util.dbloader.workers;

import java.util.concurrent.LinkedBlockingQueue;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.RecordCache;

public class PartitionSelectWorker implements Runnable {
	
	private final ConnectionDescriptor connectionDescriptor;
	private final String tableName;
	private final String schemaName;
	private final LinkedBlockingQueue<String> partitionQueue;
	private final LinkedBlockingQueue<RecordCache> cacheQueue;
	
	public PartitionSelectWorker(ConnectionDescriptor connectionDescriptor, String tableName, String schemaName,
			LinkedBlockingQueue<String> partitionQueue, LinkedBlockingQueue<RecordCache> cacheQueue) {
		this.connectionDescriptor = connectionDescriptor;
		this.tableName = tableName;
		this.schemaName = schemaName;
		this.partitionQueue = partitionQueue;
		this.cacheQueue = cacheQueue;
	}

	@Override
	public void run() {

	}

}
