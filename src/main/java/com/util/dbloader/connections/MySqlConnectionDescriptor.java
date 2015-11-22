package com.util.dbloader.connections;

public class MySqlConnectionDescriptor extends ConnectionDescriptor {

	public MySqlConnectionDescriptor(String url, String user, String pass) {
		super(url, user, pass);
	}

	@Override
	public String getDriverClassName() {
		return MYSQL_DRIVER_CLASS_NAME;
	}

}
