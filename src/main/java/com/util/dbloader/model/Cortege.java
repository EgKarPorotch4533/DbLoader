package com.util.dbloader.model;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLXML;

/**
 * Narrowed interface java.sql.ResultSet
 * @author ekorotchenko
 *
 */
public interface Cortege {

	/**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as an <code>Array</code> object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return an <code>Array</code> object representing the SQL
     *         <code>ARRAY</code> value in the specified column
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs
     * or this method is called on a closed result set
      * @exception SQLFeatureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Array getArray(int columnIndex) throws SQLException;
    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>String</code> in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    String getString(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>boolean</code> in the Java programming language.
     *
     * <P>If the designated column has a datatype of CHAR or VARCHAR
     * and contains a "0" or has a datatype of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * and contains  a 0, a value of <code>false</code> is returned.  If the designated column has a datatype
     * of CHAR or VARCHAR
     * and contains a "1" or has a datatype of BIT, TINYINT, SMALLINT, INTEGER or BIGINT
     * and contains  a 1, a value of <code>true</code> is returned.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>false</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    boolean getBoolean(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>byte</code> in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>0</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    byte getByte(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>short</code> in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>0</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    short getShort(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * an <code>int</code> in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>0</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    int getInt(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>long</code> in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>0</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    long getLong(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>float</code> in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>0</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    float getFloat(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>double</code> in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>0</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    double getDouble(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>byte</code> array in the Java programming language.
     * The bytes represent the raw values returned by the driver.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    byte[] getBytes(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>java.sql.Date</code> object in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    java.sql.Date getDate(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>java.sql.Time</code> object in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    java.sql.Time getTime(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a <code>java.sql.Timestamp</code> object in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value; if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    java.sql.Timestamp getTimestamp(int columnIndex) throws SQLException;

    /**
     * <p>Gets the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * an <code>Object</code> in the Java programming language.
     *
     * <p>This method will return the value of the given column as a
     * Java object.  The type of the Java object will be the default
     * Java object type corresponding to the column's SQL type,
     * following the mapping for built-in types specified in the JDBC
     * specification. If the value is an SQL <code>NULL</code>,
     * the driver returns a Java <code>null</code>.
     *
     * <p>This method may also be used to read database-specific
     * abstract data types.
     *
     * In the JDBC 2.0 API, the behavior of method
     * <code>getObject</code> is extended to materialize
     * data of SQL user-defined types.
     * <p>
     * If <code>Connection.getTypeMap</code> does not throw a
     * <code>SQLFeatureNotSupportedException</code>,
     * then when a column contains a structured or distinct value,
     * the behavior of this method is as
     * if it were a call to: <code>getObject(columnIndex,
     * this.getStatement().getConnection().getTypeMap())</code>.
     *
     * If <code>Connection.getTypeMap</code> does throw a
     * <code>SQLFeatureNotSupportedException</code>,
     * then structured values are not supported, and distinct values
     * are mapped to the default Java class as determined by the
     * underlying SQL type of the DISTINCT type.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a <code>java.lang.Object</code> holding the column value
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    Object getObject(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a
     * <code>java.math.BigDecimal</code> with full precision.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return the column value (full precision);
     * if the value is SQL <code>NULL</code>, the value returned is
     * <code>null</code> in the Java programming language.
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     * @since 1.2
     */
    BigDecimal getBigDecimal(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>Blob</code> object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a <code>Blob</code> object representing the SQL
     *         <code>BLOB</code> value in the specified column
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs
     * or this method is called on a closed result set
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Blob getBlob(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>Clob</code> object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a <code>Clob</code> object representing the SQL
     *         <code>CLOB</code> value in the specified column
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs
     * or this method is called on a closed result set
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Clob getClob(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as
     * a stream of ASCII characters. The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large <code>LONGVARCHAR</code> values.
     * The JDBC driver will
     * do any necessary conversion from the database format into ASCII.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream.  Also, a
     * stream may return <code>0</code> when the method
     * <code>InputStream.available</code>
     * is called whether there is data available or not.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a Java input stream that delivers the database column value
     * as a stream of one-byte ASCII characters;
     * if the value is SQL <code>NULL</code>, the
     * value returned is <code>null</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    java.io.InputStream getAsciiStream(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a  stream of
     * uninterpreted bytes. The value can then be read in chunks from the
     * stream. This method is particularly
     * suitable for retrieving large <code>LONGVARBINARY</code> values.
     *
     * <P><B>Note:</B> All the data in the returned stream must be
     * read prior to getting the value of any other column. The next
     * call to a getter method implicitly closes the stream.  Also, a
     * stream may return <code>0</code> when the method
     * <code>InputStream.available</code>
     * is called whether there is data available or not.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a Java input stream that delivers the database column value
     *         as a stream of uninterpreted bytes;
     *         if the value is SQL <code>NULL</code>, the value returned is
     *         <code>null</code>
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs or this method is
     *            called on a closed result set
     */
    java.io.InputStream getBinaryStream(int columnIndex)
        throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>NClob</code> object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a <code>NClob</code> object representing the SQL
     *         <code>NCLOB</code> value in the specified column
     * @exception SQLException if the columnIndex is not valid;
     * if the driver does not support national
     *         character sets;  if the driver can detect that a data conversion
     *  error could occur; this method is called on a closed result set
     * or if a database access error occurs
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    NClob getNClob(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>Ref</code> object
     * in the Java programming language.
     *
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a <code>Ref</code> object representing an SQL <code>REF</code>
     *         value
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs
     * or this method is called on a closed result set
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.2
     */
    Ref getRef(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in  the current row of
     *  this <code>ResultSet</code> as a
     * <code>java.sql.SQLXML</code> object in the Java programming language.
     * @param columnIndex the first column is 1, the second is 2, ...
     * @return a <code>SQLXML</code> object that maps an <code>SQL XML</code> value
     * @throws SQLException if the columnIndex is not valid;
     * if a database access error occurs
     * or this method is called on a closed result set
     * @exception SQLFeatureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.6
     */
    SQLXML getSQLXML(int columnIndex) throws SQLException;

    /**
     * Retrieves the value of the designated column in the current row
     * of this <code>ResultSet</code> object as a <code>java.net.URL</code>
     * object in the Java programming language.
     *
     * @param columnIndex the index of the column 1 is the first, 2 is the second,...
     * @return the column value as a <code>java.net.URL</code> object;
     * if the value is SQL <code>NULL</code>,
     * the value returned is <code>null</code> in the Java programming language
     * @exception SQLException if the columnIndex is not valid;
     * if a database access error occurs; this method
     * is called on a closed result set or if a URL is malformed
      * @exception SQLFeatureNotSupportedException if the JDBC driver does not support
     * this method
     * @since 1.4
     */
    java.net.URL getURL(int columnIndex) throws SQLException;

}
