package com.util.dbloader.configurations;

public class Mapping {
	
	private EndPoint source;
	private EndPoint destination;
	// Could be '*' or list of format: col1,col2,col3
	private String columns;
	
	public EndPoint getSource() {
		return source;
	}
	public EndPoint getDestination() {
		return destination;
	}
	public String getColumns() {
		return columns;
	}
	public void setSource(EndPoint source) {
		this.source = source;
	}
	public void setDestination(EndPoint destination) {
		this.destination = destination;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
}
