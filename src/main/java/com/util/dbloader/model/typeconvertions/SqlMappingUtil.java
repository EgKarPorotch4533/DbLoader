package com.util.dbloader.model.typeconvertions;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.dbloader.model.Metadata;
import com.util.dbloader.model.RecordCache;

public class SqlMappingUtil {
	
	/**
	 * Suppose there is already constructed by PreparedInsert statement according to exact given Metadata. Will not work with
	 * another statements due to defined order!
	 * @param st
	 * @param md
	 * @param r
	 * @throws SQLException
	 */
	public static void evaluateStatement(PreparedStatement st, Metadata md, RecordCache r, boolean caseOfOracle) throws SQLException {
		for (int i = 0; i < md.getColumnCount(); i++) {
			int column = i + 1;
			int sqlType = md.getColumnType(column);
			switch(sqlType) {
			case java.sql.Types.ARRAY:
				st.setArray(column, r.getArray(column));
				break;
			case java.sql.Types.BIGINT:
				st.setLong(column, r.getLong(column));
				break;
			case java.sql.Types.BINARY:
				st.setBytes(column, r.getBytes(column));
				break;
			case java.sql.Types.BIT:
				st.setBoolean(column, r.getBoolean(column));
				break;
			case java.sql.Types.BLOB:
				if (caseOfOracle) {
					if (r.getBlob(column) == null) {
						st.setBytes(column, null);
					} else {
						st.setBytes(column, r.getBlob(column).getBytes(1, (int) r.getBlob(column).length()));
					}
				} else {
					st.setBlob(column, r.getBlob(column));
				}
				break;
			case java.sql.Types.BOOLEAN:
				st.setBoolean(column, r.getBoolean(column));
				break;
			case java.sql.Types.CHAR:
				st.setString(column, r.getString(column));
				break;
			case java.sql.Types.CLOB:
				st.setClob(column, r.getClob(column));
				break;
			case java.sql.Types.DATALINK:
				st.setURL(column, r.getURL(column));
				break;
			case java.sql.Types.DATE:
				st.setDate(column, r.getDate(column));
				break;
			case java.sql.Types.DECIMAL:
				st.setBigDecimal(column, r.getBigDecimal(column));
				break;
			case java.sql.Types.DISTINCT:
				st.setString(column, r.getString(column));
				break;
			case java.sql.Types.DOUBLE:
				st.setDouble(column, r.getDouble(column));
				break;
			case java.sql.Types.FLOAT:
				st.setDouble(column, r.getDouble(column));
				break;
			case java.sql.Types.INTEGER:
				st.setInt(column, r.getInt(column));
				break;
			case java.sql.Types.JAVA_OBJECT:
				st.setObject(column, r.getObject(column));
				break;
			case java.sql.Types.LONGVARCHAR:
			case java.sql.Types.LONGNVARCHAR:
				st.setAsciiStream(column, r.getAsciiStream(column));
				break;
			case java.sql.Types.LONGVARBINARY:
				st.setBinaryStream(column, r.getBinaryStream(column));
				break;
			case java.sql.Types.NCHAR:
				st.setString(column, r.getString(column));
				break;
			case java.sql.Types.NCLOB:
				st.setNClob(column, r.getNClob(column));
				break;
			case java.sql.Types.NULL:
				st.setNull(column, sqlType);
				break;
			case java.sql.Types.NUMERIC:
				st.setBigDecimal(column, r.getBigDecimal(column));
				break;
			case java.sql.Types.NVARCHAR:
				st.setString(column, r.getString(column));
				break;
			case java.sql.Types.OTHER:
				st.setObject(column, r.getObject(column));
				break;
			case java.sql.Types.REAL:
				st.setFloat(column, r.getFloat(column));
				break;
			case java.sql.Types.REF:
				st.setRef(column, r.getRef(column));
				break;
			case java.sql.Types.SMALLINT:
				st.setShort(column, r.getShort(column));
				break;
			case java.sql.Types.SQLXML:
				st.setSQLXML(column, r.getSQLXML(column));
				break;
			case java.sql.Types.STRUCT:
				st.setObject(column, r.getObject(column));
				break;
			case java.sql.Types.TIME:
				st.setTime(column, r.getTime(column));
				break;
			case java.sql.Types.TIMESTAMP:
				st.setTimestamp(column, r.getTimestamp(column));
				break;
			case java.sql.Types.TINYINT:
				st.setByte(column, r.getByte(column));
				break;
			case java.sql.Types.VARBINARY:
				st.setBytes(column, r.getBytes(column));
				break;
			case java.sql.Types.VARCHAR:
				st.setString(column, r.getString(column));
				break;
			}
		}
	}
}
