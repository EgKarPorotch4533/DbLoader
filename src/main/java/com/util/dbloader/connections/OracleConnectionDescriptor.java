package com.util.dbloader.connections;

public class OracleConnectionDescriptor extends ConnectionDescriptor {

	public OracleConnectionDescriptor(String tns, String user, String pass) {
		super(String.format("jdbc:oracle:thin@%s", tns), user, pass);
	}
	
	@Override
	public String getDriverClassName() {
		return ORACLE_DRIVER_CLASS_NAME;
	}

}
