package com.util.dbloader.connections;

public class PostgreConnectionDescriptor extends ConnectionDescriptor {

	public PostgreConnectionDescriptor(String url, String user, String pass) {
		super(url, user, pass);
	}

	@Override
	public String getDriverClassName() {
		return POSTGRES_DRIVER_CLASS_NAME;
	}

}
