package com.util.dbloader.workers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.RecordCache;
import com.util.dbloader.model.statements.SelectFromTable;

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
		Connection connection = null;
		try {
			connection = connectionDescriptor.createConnection();
			while(!partitionQueue.isEmpty()) {
				String partition = partitionQueue.poll();
				System.out.printf("starting read new partition %s from table %s.%s", partition, schemaName, tableName);
				try {
					ResultSet rs = connection.createStatement().executeQuery(
							new SelectFromTable(tableName, schemaName, partition).getQuery());
					cacheQueue.put(new RecordCache(rs));
				} catch (SQLException e1) {
					System.err.printf("sql exception while reading partition (%s) of table (%s) of schema (%s)%n", partition, tableName, schemaName);
				} catch (InterruptedException e) {
					System.err.println("interrupted operation of adding item to cache queue");
					e.printStackTrace();
				}
			}
		} catch (SQLException | ClassNotFoundException e1) {
			System.err.println("failed to open connection to DB, worker exits");
			e1.printStackTrace();
			return;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) { /* ignore */ }
			}
		}
	}

}
