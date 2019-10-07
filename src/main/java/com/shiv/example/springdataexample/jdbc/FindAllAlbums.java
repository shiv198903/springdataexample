package com.shiv.example.springdataexample.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.MappingSqlQuery;

public class FindAllAlbums extends MappingSqlQuery<Album> {

	static String FIND_ALL_ALBUMS = "SELECT ID, TITLE, RELEASE_DATE,SINGER_ID FROM ALBUM";

	@Autowired
	DataSource dataSource;

	public FindAllAlbums(DataSource dataSource) {
		super(dataSource, FIND_ALL_ALBUMS);
	}

	@Override
	protected Album mapRow(ResultSet rs, int rowNum) throws SQLException {
		Album album = new Album();
		album.setId(rs.getLong("id"));
		album.setTitle(rs.getString("title"));
		album.setReleaseDate(rs.getDate("release_date"));
		album.setSingerId(rs.getLong("singer_id"));
		return album;
	}

}
