package com.util.dbloader.workers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.Metadata;
import com.util.dbloader.model.RecordCache;
import com.util.dbloader.model.statements.PreparedInsert;
import com.util.dbloader.model.typeconvertions.SqlMappingUtil;

public class BulkInsertWorker implements Runnable {

	private final ConnectionDescriptor connectionDescriptor;
	private final String tableName;
	private final String schemaName;
	private final LinkedBlockingQueue<RecordCache> cacheQueue;
	private final int bulkSize;
	private final Metadata md;
	
	public BulkInsertWorker(ConnectionDescriptor connectionDescriptor, Metadata md, String tableName, String schemaName,
			LinkedBlockingQueue<RecordCache> cacheQueue, int bulkSize) throws SQLException {
		this.connectionDescriptor = connectionDescriptor;
		this.tableName = tableName;
		this.schemaName = schemaName;
		this.cacheQueue = cacheQueue;
		this.bulkSize = bulkSize;
		this.md = md;
	}
	
	@Override
	public void run() {
		Connection connection = null;
		try {
			connection = connectionDescriptor.createConnection();
			connection.setAutoCommit(false);
			List<RecordCache> cacheList = new ArrayList<RecordCache>();
			while (!cacheQueue.isEmpty()) {
				cacheList.clear();
				cacheQueue.drainTo(cacheList, bulkSize);
				try {
					pushSlice(connection, cacheList);
				} catch (SQLException e1) {
					System.err.printf("sql exception while writing to table (%s) of schema (%s)\n", tableName, schemaName);
					e1.printStackTrace();
				}
				try {
					// wait for others..
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.printf("[%d] me was interrupted...\n", Thread.currentThread().getId());
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("failed to open connection to DB, worker exits");
			e.printStackTrace();
			return;
		}
	}
	
	private void pushSlice(Connection connection, List<RecordCache> cacheList) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(
				new PreparedInsert(md, tableName, schemaName).getNamedInsert());
		System.out.printf("[%d] inserting patch...\n", Thread.currentThread().getId());
		for (RecordCache record : cacheList) {
			SqlMappingUtil.evaluateStatement(ps, md, record, connectionDescriptor
					.getDriverClassName().toLowerCase().contains("oracle"));
			ps.addBatch();
		}
		ps.executeBatch();
		connection.commit();
	}

}
