package com.util.dbloader.configurations;

public class EndPoint {
	
	private Connection connection;
	private String tableName;
	private String schemaName;

	public Connection getConnection() {
		return connection;
	}
	public String getTableName() {
		return tableName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
