package com.util.dbloader.workers;

import java.util.concurrent.LinkedBlockingQueue;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.RecordCache;

public class BulkInsertWorker implements Runnable {

	private final ConnectionDescriptor connectionDescriptor;
	private final String tableName;
	private final String schemaName;
	private final LinkedBlockingQueue<RecordCache> cacheQueue;
	private final int bulkSize;
	
	public BulkInsertWorker(ConnectionDescriptor connectionDescriptor, String tableName, String schemaName,
			LinkedBlockingQueue<RecordCache> cacheQueue, int bulkSize) {
		this.connectionDescriptor = connectionDescriptor;
		this.tableName = tableName;
		this.schemaName = schemaName;
		this.cacheQueue = cacheQueue;
		this.bulkSize = bulkSize;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
