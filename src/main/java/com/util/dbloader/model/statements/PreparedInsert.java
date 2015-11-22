package com.util.dbloader.model.statements;

import java.sql.SQLException;

import com.util.dbloader.model.Metadata;

public class PreparedInsert {

	private static final String TEMPLATE_NAMED = "INSERT INTO %s.%s (%s) VALUES (%s)";
	private static final String TEMPLATE_ANONIMOUS = "INSERT INTO %s.%s VALUES (%s)";
	
	private final String namedInsert;
	private final String anonimousInsert;
	
	public PreparedInsert(Metadata md, String tableName, String schemaName) throws SQLException {
		StringBuilder sbColumns = new StringBuilder();
		StringBuilder sbValues = new StringBuilder();
		for (int i = 0; i < md.getColumnCount(); i++) {
			if (i > 0) {
				sbColumns.append(',');
				sbValues.append(',');
			}
			int colnum = i + 1;
			sbColumns.append(md.getColumnName(colnum));
			sbValues.append('?');
		}
		namedInsert = String.format(TEMPLATE_NAMED, schemaName, tableName, sbColumns.toString(), sbValues.toString());
		anonimousInsert = String.format(TEMPLATE_ANONIMOUS, schemaName, tableName, sbValues.toString());
	}

	public String getNamedInsert() {
		return namedInsert;
	}

	public String getAnonimousInsert() {
		return anonimousInsert;
	}
}
