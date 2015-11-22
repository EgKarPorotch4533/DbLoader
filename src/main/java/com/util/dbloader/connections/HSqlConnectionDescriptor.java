package com.util.dbloader.connections;

public class HSqlConnectionDescriptor extends ConnectionDescriptor {

	public HSqlConnectionDescriptor(String url, String user, String pass) {
		super(url, user, pass);
	}

	@Override
	public String getDriverClassName() {
		return HSQL_DRIVER_CLASS_NAME;
	}

}
