package com.util.dbloader.model.statements;

import java.sql.SQLException;

import com.util.dbloader.model.Metadata;

public class PreparedInsert {

	private static final String TEMPLATE_NAMED = "INSERT INTO %s (%s) VALUES (%s)";
	private static final String TEMPLATE_ANONIMOUS = "INSERT INTO %s VALUES (%s)";
	
	private final String namedInsert;
	private final String anonimousInsert;
	
	public PreparedInsert(Metadata md) throws SQLException {
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
		namedInsert = String.format(TEMPLATE_NAMED, sbColumns.toString(), sbValues.toString());
		anonimousInsert = String.format(TEMPLATE_ANONIMOUS, sbValues.toString());
	}

	public String getNamedInsert() {
		return namedInsert;
	}

	public String getAnonimousInsert() {
		return anonimousInsert;
	}
}
