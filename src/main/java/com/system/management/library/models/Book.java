package com.system.management.library.models;

public class Book {
	private int Id;
	private String ISBN;
	private String Title;
	private String Author;
	private String Summary;
	private int YearPublished;
	private String ImageUrl;
	public Book(int id, String iSBN, String title, String author, String summary, int yearPublished, String imageUrl) {
		super();
		Id = id;
		ISBN = iSBN;
		Title = title;
		Author = author;
		Summary = summary;
		YearPublished = yearPublished;
		ImageUrl = imageUrl;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public int getYearPublished() {
		return YearPublished;
	}
	public void setYearPublished(int yearPublished) {
		YearPublished = yearPublished;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

}
