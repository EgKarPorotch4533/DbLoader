package com.util.dbloader.connections;

import java.util.Arrays;

public class ConnectionFactory {

	public static ConnectionDescriptor getConnectionDescriptor(com.util.dbloader.configurations.Connection connection) throws Exception {
		switch (connection.getVendor()) {
		case ORACLE:
			return new OracleConnectionDescriptor(connection.getUrl(), connection.getUser(), connection.getPass());
		case MYSQL:
			return new MySqlConnectionDescriptor(connection.getUrl(), connection.getUser(), connection.getPass());
		case POSTGRESQL:
			return new PostgreConnectionDescriptor(connection.getUrl(), connection.getUser(), connection.getPass());
		case HSQL:
			return new HSqlConnectionDescriptor(connection.getUrl(), connection.getUser(), connection.getPass());
			default:
				throw new Exception(String.format("database vendor is not recognized, choose one of: %s",
						Arrays.toString(com.util.dbloader.configurations.Connection.Vendors.values())));
		}
	}
}
