package com.system.management.library.models;

public class PhysicalBook {
	private int id;
	private String location;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PhysicalBook(int id, String location, String status) {
		super();
		this.id = id;
		this.location = location;
		this.status = status;
	}
	
}
