package com.shiv.example.springdataexample.jdbc;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public interface AlbumDao {
	
	void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate);

	List<Album> findAll();

	Album findById(long id);
	
	Album findByTitle(String title);
	
	int insert(Album album);

	int update(Album album);

	int delete(Album album);
	
	List<Album> findAllWithSingers();

}
