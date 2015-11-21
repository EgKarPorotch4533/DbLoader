package com.util.dbloader.configurations;

public class Connection {
	
	public enum Vendors { ORACLE, MYSQL, POSTGRESSQL }

	private String url;
	private String user;
	private String pass;
	private Vendors vendor;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Vendors getVendor() {
		return vendor;
	}
	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}
}
