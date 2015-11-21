package com.util.dbloader.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.dbloader.connections.ConnectionDescriptor;
import com.util.dbloader.model.Metadata;
import com.util.dbloader.model.statements.SelectFromTable;

public class SourceReader {

	ConnectionDescriptor descriptor;
	Connection connection;
	
	public SourceReader(ConnectionDescriptor descriptor) {
		this.descriptor = descriptor;
		this.connection = null;
	}
	
	public Metadata fetchMetafata(String tableName, String schemaName) throws SQLException {
		try {
			if (connection == null) {
				connection = descriptor.createConnection();
			}
			PreparedStatement preparedStatement = connection.prepareStatement(
					new SelectFromTable(tableName, schemaName).getQuery());
			preparedStatement.setFetchSize(1);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return new Metadata(rs.getMetaData());
		} finally {
			close();
		}
	}
	
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {}
		}
	}
}
