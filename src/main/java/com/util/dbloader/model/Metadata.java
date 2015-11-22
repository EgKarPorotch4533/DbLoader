package com.util.dbloader.model;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Wrapper is used to cache metadata of java.sql.ResultSet
 * @author ekorotchenko
 *
 */
public class Metadata implements ResultSetMetaData {
	
	int columnCount;
	boolean[] autoincrements;
	boolean[] casesensitives;
	boolean[] searchables;
	boolean[] currencies;
	int[] nullables;
	boolean[] signeditems;
	int[] displaysizes;
	String[] columnlables;
	String[] columnnames;
	String[] schemanames;
	int[] precisions;
	int[] scales;
	String[] tablenames;
	String[] catalognames;
	int[] columntypes;
	String[] columntypenames;
	boolean[] readonlys;
	boolean[] writables;
	boolean[] definitlywritables;
	String[] columnclassnames;
	
	public Metadata(ResultSetMetaData rsmd) throws SQLException {
		columnCount = rsmd.getColumnCount();
		autoincrements = new boolean[columnCount];
		casesensitives = new boolean[columnCount];
		searchables = new boolean[columnCount];
		currencies = new boolean[columnCount];
		nullables = new int[columnCount];
		signeditems = new boolean[columnCount];
		displaysizes = new int[columnCount];
		columnlables = new String[columnCount];
		columnnames = new String[columnCount];
		schemanames = new String[columnCount];
		precisions = new int[columnCount];
		scales = new int[columnCount];
		tablenames = new String[columnCount];
		catalognames = new String[columnCount];
		columntypes = new int[columnCount];
		columntypenames = new String[columnCount];
		readonlys = new boolean[columnCount];
		writables = new boolean[columnCount];
		definitlywritables = new boolean[columnCount];
		columnclassnames = new String[columnCount];
		for (int i = 0; i < columnCount; i++) {
			int colNum = i + 1;
			autoincrements[i] = rsmd.isAutoIncrement(colNum);
			casesensitives[i] = rsmd.isCaseSensitive(colNum);
			searchables[i] = rsmd.isSearchable(colNum);
			currencies[i] = rsmd.isCurrency(colNum);
			nullables[i] = rsmd.isNullable(colNum);
			signeditems[i] = rsmd.isSigned(colNum);
			displaysizes[i] = rsmd.getColumnDisplaySize(colNum);
			columnlables[i] = rsmd.getColumnLabel(colNum);
			columnnames[i] = rsmd.getColumnName(colNum);
			schemanames[i] = rsmd.getSchemaName(colNum);
			precisions[i] = rsmd.getPrecision(colNum);
			scales[i] = rsmd.getScale(colNum);
			tablenames[i] = rsmd.getTableName(colNum);
			catalognames[i] = rsmd.getCatalogName(colNum);
			columntypes[i] = rsmd.getColumnType(colNum);
			columntypenames[i] = rsmd.getColumnTypeName(colNum);
			readonlys[i] = rsmd.isReadOnly(colNum);
			writables[i] = rsmd.isWritable(colNum);
			definitlywritables[i] = rsmd.isDefinitelyWritable(colNum);
			columnclassnames[i] = rsmd.getColumnClassName(colNum);
		}
	}

	/*
	 *  stub(non-Javadoc)
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException { return null; }

	/*
	 *  stub(non-Javadoc)
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException { return false; }

	@Override
	public int getColumnCount() throws SQLException {
		return columnCount;
	}

	@Override
	public boolean isAutoIncrement(int column) throws SQLException {
		return autoincrements[column - 1];
	}

	@Override
	public boolean isCaseSensitive(int column) throws SQLException {
		return casesensitives[column - 1];
	}

	@Override
	public boolean isSearchable(int column) throws SQLException {
		return searchables[column - 1];
	}

	@Override
	public boolean isCurrency(int column) throws SQLException {
		return currencies[column - 1];
	}

	@Override
	public int isNullable(int column) throws SQLException {
		return nullables[column - 1];
	}

	@Override
	public boolean isSigned(int column) throws SQLException {
		return signeditems[column - 1];
	}

	@Override
	public int getColumnDisplaySize(int column) throws SQLException {
		return displaysizes[column - 1];
	}

	@Override
	public String getColumnLabel(int column) throws SQLException {
		return columnlables[column - 1];
	}

	@Override
	public String getColumnName(int column) throws SQLException {
		return columnnames[column - 1];
	}

	@Override
	public String getSchemaName(int column) throws SQLException {
		return schemanames[column - 1];
	}

	@Override
	public int getPrecision(int column) throws SQLException {
		return precisions[column - 1];
	}

	@Override
	public int getScale(int column) throws SQLException {
		return scales[column - 1];
	}

	@Override
	public String getTableName(int column) throws SQLException {
		return tablenames[column - 1];
	}

	@Override
	public String getCatalogName(int column) throws SQLException {
		return catalognames[column - 1];
	}

	@Override
	public int getColumnType(int column) throws SQLException {
		return columntypes[column - 1];
	}

	@Override
	public String getColumnTypeName(int column) throws SQLException {
		return columntypenames[column - 1];
	}

	@Override
	public boolean isReadOnly(int column) throws SQLException {
		return readonlys[column - 1];
	}

	@Override
	public boolean isWritable(int column) throws SQLException {
		return writables[column - 1];
	}

	@Override
	public boolean isDefinitelyWritable(int column) throws SQLException {
		return definitlywritables[column - 1];
	}

	@Override
	public String getColumnClassName(int column) throws SQLException {
		return columnclassnames[column - 1];
	}
}
