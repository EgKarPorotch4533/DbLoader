package com.util.dbloader.samples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.statements.SelectPartitions;

public class PartitionCollector {

	private final ConnectionDescriptor descriptor;
	
	public PartitionCollector(ConnectionDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	/**
	 * List all partitions for specified table
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<String> list(String tableName, String schemaName) throws SQLException, ClassNotFoundException {
		Connection connection = descriptor.createConnection();
		System.out.printf("[PartitionCollector] start grep partitions...%n");
		ResultSet rs = connection.createStatement()
				.executeQuery(new SelectPartitions(tableName, schemaName).getQuery());
		List<String> lst = new ArrayList<String>();
		while(rs.next()) {
			lst.add(rs.getString(1));
			System.out.printf("[PartitionCollector] partition_name: %s%n");
		}
		connection.close();
		return lst;
	}
}
