package com.system.management.library.models;

import java.util.Date;

public class Reservation {
	private int Id;
	private String UserEmail;
	private Date DateReserved;
	private String BookTitle;
	private String Status;
	private String Location;
	public Reservation(int id, String userEmail, Date dateReserved, String bookTitle, String status, String location) {
		super();
		Id = id;
		UserEmail = userEmail;
		DateReserved = dateReserved;
		BookTitle = bookTitle;
		Status = status;
		Location = location;
	}
	
	public String getBookTitle() {
		return BookTitle;
	}

	public void setBookTitle(String bookTitle) {
		BookTitle = bookTitle;
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public Date getDateReserved() {
		return DateReserved;
	}
	public void setDateReserved(Date dateReserved) {
		DateReserved = dateReserved;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	

}
