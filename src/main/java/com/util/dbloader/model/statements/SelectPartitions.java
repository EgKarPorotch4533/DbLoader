package com.util.dbloader.model.statements;

public class SelectPartitions {
	
	private static final String TEMPLATE = "SELECT PARTITION_NAME FROM ALL_TAB_PARTITIONS WHERE TABLE_NAME = '%s' AND TABLE_OWNER = '%s'";
	
	private final String query;
	
	public SelectPartitions(String tableName, String schemaName) {
		query = String.format(TEMPLATE, tableName, schemaName);
	}
	
	public String getQuery() {
		return query;
	}
}
