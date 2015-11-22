package com.util.dbloader.model;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Narrowed cache for java.sql.ResultSet
 * @author ekorotchenko
 *
 */
public class RecordCache implements Cortege {
	
	String[] strings;
	boolean[] booleans;
	byte[] bytes;
	short[] shorts;
	int[] ints;
	long[] longs;
	float[] floats;
	double[] doubles;
	byte[][] bytess;
	java.sql.Date[] dates;
	java.sql.Time[] times;
	Timestamp[] timestamps;
	Object[] objects;
	Array[] arrays;
	BigDecimal[] bigdecimals;
	Blob[] blobs;
	Clob[] clobs;
	java.io.InputStream[] asciistreams;
	java.io.InputStream[] binarystreams;
	NClob[] nclobs;
	Ref[] refs;
	SQLXML[] sqlxmls;
	URL[] urls;
	
	public RecordCache(ResultSet rs) throws SQLException {
		int cnt = rs.getMetaData().getColumnCount();
		strings = new String[cnt];
		booleans = new boolean[cnt];
		bytes = new byte[cnt];
		ints = new int[cnt];
		longs = new long[cnt];
		floats = new float[cnt];
		doubles = new double[cnt];
		bytess = new byte[cnt][];
		dates = new java.sql.Date[cnt];
		times = new java.sql.Time[cnt];
		timestamps = new Timestamp[cnt];
		objects = new Object[cnt];
		arrays = new Array[cnt];
		bigdecimals = new BigDecimal[cnt];
		blobs = new Blob[cnt];
		clobs = new Clob[cnt];
		asciistreams = new java.io.InputStream[cnt];
		binarystreams = new java.io.InputStream[cnt];
		nclobs = new NClob[cnt];
		refs = new Ref[cnt];
		sqlxmls = new SQLXML[cnt];
		urls = new URL[cnt];
		for (int i = 0; i < cnt; i++) {
			int columnIndex = i + 1;
			switch (rs.getMetaData().getColumnType(columnIndex)) {
			case java.sql.Types.ARRAY:
				arrays[i] = rs.getArray(columnIndex);
				break;
			case java.sql.Types.BIGINT:
				longs[i] = rs.getLong(columnIndex);
				break;
			case java.sql.Types.BINARY:
				bytess[i] = rs.getBytes(columnIndex);
				break;
			case java.sql.Types.BIT:
				booleans[i] = rs.getBoolean(columnIndex);
				break;
			case java.sql.Types.BLOB:
				blobs[i] = rs.getBlob(columnIndex);
				break;
			case java.sql.Types.BOOLEAN:
				booleans[i] = rs.getBoolean(columnIndex);
				break;
			case java.sql.Types.CHAR:
				strings[i] = rs.getString(columnIndex);
				break;
			case java.sql.Types.CLOB:
				clobs[i] = rs.getClob(columnIndex);
				break;
			case java.sql.Types.DATALINK:
				urls[i] = rs.getURL(columnIndex);
				break;
			case java.sql.Types.DATE:
				dates[i] = rs.getDate(columnIndex);
				break;
			case java.sql.Types.DECIMAL:
				bigdecimals[i] = rs.getBigDecimal(columnIndex);
				break;
			case java.sql.Types.DISTINCT:
				strings[i] = rs.getString(columnIndex);
				break;
			case java.sql.Types.DOUBLE:
				doubles[i] = rs.getDouble(columnIndex);
				break;
			case java.sql.Types.FLOAT:
				doubles[i] = rs.getDouble(columnIndex);
				break;
			case java.sql.Types.INTEGER:
				ints[i] = rs.getInt(columnIndex);
				break;
			case java.sql.Types.JAVA_OBJECT:
				objects[i] = rs.getObject(columnIndex);
				break;
			case java.sql.Types.LONGVARCHAR:
			case java.sql.Types.LONGNVARCHAR:
				asciistreams[i] = rs.getAsciiStream(columnIndex);
				break;
			case java.sql.Types.LONGVARBINARY:
				binarystreams[i] = rs.getBinaryStream(columnIndex);
				break;
			case java.sql.Types.NCHAR:
				strings[i] = rs.getString(columnIndex);
				break;
			case java.sql.Types.NCLOB:
				nclobs[i] = rs.getNClob(columnIndex);
				break;
			case java.sql.Types.NUMERIC:
				bigdecimals[i] = rs.getBigDecimal(columnIndex);
				break;
			case java.sql.Types.NVARCHAR:
				strings[i] = rs.getString(columnIndex);
				break;
			case java.sql.Types.OTHER:
				objects[i] = rs.getObject(columnIndex);
				break;
			case java.sql.Types.REAL:
				floats[i] = rs.getFloat(columnIndex);
				break;
			case java.sql.Types.REF:
				refs[i] = rs.getRef(columnIndex);
				break;
			case java.sql.Types.SMALLINT:
				shorts[i] = rs.getShort(columnIndex);
				break;
			case java.sql.Types.SQLXML:
				sqlxmls[i] = rs.getSQLXML(columnIndex);
				break;
			case java.sql.Types.STRUCT:
				objects[i] = rs.getObject(columnIndex);
				break;
			case java.sql.Types.TIME:
				times[i] = rs.getTime(columnIndex);
				break;
			case java.sql.Types.TIMESTAMP:
				timestamps[i] = rs.getTimestamp(columnIndex);
				break;
			case java.sql.Types.TINYINT:
				bytes[i] = rs.getByte(columnIndex);
				break;
			case java.sql.Types.VARBINARY:
				bytess[i] = rs.getBytes(columnIndex);
				break;
			case java.sql.Types.VARCHAR:
				strings[i] = rs.getString(columnIndex);
				break;
			}
		}
	}

	@Override
	public String getString(int columnIndex) throws SQLException {
		return strings[columnIndex - 1];
	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		return booleans[columnIndex - 1];
	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		return bytes[columnIndex - 1];
	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		return shorts[columnIndex - 1];
	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		return ints[columnIndex - 1];
	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		return longs[columnIndex - 1];
	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		return floats[columnIndex - 1];
	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		return doubles[columnIndex - 1];
	}

	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		return bytess[columnIndex - 1];
	}

	@Override
	public Date getDate(int columnIndex) throws SQLException {
		return dates[columnIndex - 1];
	}

	@Override
	public Time getTime(int columnIndex) throws SQLException {
		return times[columnIndex - 1];
	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return timestamps[columnIndex - 1];
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		return objects[columnIndex - 1];
	}

	@Override
	public Array getArray(int columnIndex) throws SQLException {
		return arrays[columnIndex - 1];
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		return bigdecimals[columnIndex - 1];
	}

	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		return blobs[columnIndex - 1];
	}

	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		return clobs[columnIndex - 1];
	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		return asciistreams[columnIndex - 1];
	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		return binarystreams[columnIndex - 1];
	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		return nclobs[columnIndex - 1];
	}

	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		return refs[columnIndex - 1];
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		return sqlxmls[columnIndex - 1];
	}

	@Override
	public URL getURL(int columnIndex) throws SQLException {
		return urls[columnIndex - 1];
	}
}
