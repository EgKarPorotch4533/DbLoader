package com.util.dbloader.workers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.RecordCache;
import com.util.dbloader.model.statements.SelectFromTable;

public class SelectWorker implements Runnable {
	
	private final ConnectionDescriptor connectionDescriptor;
	private final String tableName;
	private final String schemaName;
	private final LinkedBlockingQueue<RecordCache> cacheQueue;

	public SelectWorker(ConnectionDescriptor connectionDescriptor, String tableName, String schemaName, LinkedBlockingQueue<RecordCache> cacheQueue) {
		this.connectionDescriptor = connectionDescriptor;
		this.tableName = tableName;
		this.schemaName = schemaName;
		this.cacheQueue = cacheQueue;
	}

	@Override
	public void run() {
		Connection connection = null;
		try {
			connection = connectionDescriptor.createConnection();
			System.out.printf("[%d] starting read from table %s.%s", Thread.currentThread().getId(), schemaName, tableName);
			try {
				ResultSet rs = connection.createStatement().executeQuery(
						new SelectFromTable(tableName, schemaName).getQuery());
				while (rs.next()) {
					cacheQueue.put(new RecordCache(rs));
				}
			} catch (SQLException e1) {
				System.err.printf("[%d] sql exception while reading of table (%s) of schema (%s)%n", Thread.currentThread().getId(), tableName, schemaName);
			} catch (InterruptedException e) {
				System.err.printf("[%d] interrupted operation of adding item to cache queue%n", Thread.currentThread().getId());
				e.printStackTrace();
			}
		} catch (SQLException | ClassNotFoundException e1) {
			System.err.printf("[%d] failed to open connection to DB, worker exits%n", Thread.currentThread().getId());
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
