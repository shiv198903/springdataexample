package com.shiv.example.springdataexample.jdbc;

import java.io.Serializable;
import java.sql.Date;

public class Album implements Serializable {

	private static final long serialVersionUID = 8964552674860197180L;
	private long id;
	private long singerId;
	private String title;
	private Date releaseDate;

	protected Album() {
		super();
	}

	public Album(long id, long singerId, String title, Date releaseDate) {
		super();
		this.id = id;
		this.singerId = singerId;
		this.title = title;
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSingerId() {
		return singerId;
	}

	public void setSingerId(long singerId) {
		this.singerId = singerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
