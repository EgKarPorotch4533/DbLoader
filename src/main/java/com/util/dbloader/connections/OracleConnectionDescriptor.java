package com.util.dbloader.connections;

public class OracleConnectionDescriptor extends ConnectionDescriptor {

	public OracleConnectionDescriptor(String url, String user, String pass) {
		super(url, user, pass);
	}
	
	@Override
	public String getDriverClassName() {
		return ORACLE_DRIVER_CLASS_NAME;
	}

}
