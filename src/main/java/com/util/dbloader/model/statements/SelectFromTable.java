package com.util.dbloader.model.statements;

/**
 * Oracle style
 * @author ekorotchenko
 *
 */
public class SelectFromTable {
	
	private static final String TEMPLATE = "SELECT * FROM ";
	private static final String PARTITION = "PARTITION";
	
	private final String query;
	
	public SelectFromTable(String tableName, String schemaName, String ... partitions) {
		StringBuilder qb = new StringBuilder();
		qb.append(TEMPLATE);
		if (schemaName != null && schemaName.length() > 0) {
			qb.append(schemaName).append('.');
		}
		qb.append(tableName);
		if (partitions.length > 0) {
			qb.append(' ');
			qb.append(PARTITION);
			qb.append(' ');
			qb.append('(');
			int k = 0;
			for (String partition : partitions) {
				if (k > 0) {
					qb.append(',');
				}
				qb.append(partition);
				k++;
			}
			qb.append(')');
		}
		query = qb.toString();
	}
	
	public String getQuery() {
		return query;
	}
}
