package com.util.dbloader.workers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.Metadata;
import com.util.dbloader.model.RecordCache;
import com.util.dbloader.model.statements.PreparedInsert;

public class BulkInsertWorker implements Runnable {

	private final ConnectionDescriptor connectionDescriptor;
	private final String tableName;
	private final String schemaName;
	private final LinkedBlockingQueue<RecordCache> cacheQueue;
	private final int bulkSize;
	
	public BulkInsertWorker(ConnectionDescriptor connectionDescriptor, Metadata md,
			LinkedBlockingQueue<RecordCache> cacheQueue, int bulkSize) throws SQLException {
		this.connectionDescriptor = connectionDescriptor;
		this.tableName = md.getTableName(1);
		this.schemaName = md.getSchemaName(1);
		this.cacheQueue = cacheQueue;
		this.bulkSize = bulkSize;
	}
	
	@Override
	public void run() {
		Connection connection = null;
		try {
			connection = connectionDescriptor.createConnection();
			List<RecordCache> cacheList = new ArrayList<RecordCache>();
			while (cacheQueue.isEmpty()) {
				int k = bulkSize;
				cacheList.clear();
				while(k-- > 0 && !cacheQueue.isEmpty()) {
					cacheList.add(cacheQueue.poll());
				}
				try {
					pushSlice(connection, cacheList);
				} catch (SQLException e1) {
					System.err.printf("sql exception while writing to table (%s) of schema (%s)%n", tableName, schemaName);
				}
			}
		} catch (SQLException e) {
			System.err.println("failed to open connection to DB, worker exits");
			e.printStackTrace();
			return;
		}
	}
	
	private void pushSlice(Connection connection, List<RecordCache> cacheList) throws SQLException {
		connection.prepareStatement(new PreparedInsert(null).getNamedInsert());
	}

}
