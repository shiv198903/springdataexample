package com.shiv.example.springdataexample.jdbc;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Singer implements Serializable {

	private static final long serialVersionUID = -7964757816934242761L;
	private long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<Album> albums;

	protected Singer() {
		super();
	}

	public Singer(long id, String firstName, String lastName, Date birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public boolean addAlbum(Album album) {
		if (albums == null) {
			albums = new ArrayList<Album>();
			albums.add(album);
			return true;
		} else if (albums.contains(album)) {
			return false;
		} else {
			albums.add(album);
			return true;
		}
	}

}
