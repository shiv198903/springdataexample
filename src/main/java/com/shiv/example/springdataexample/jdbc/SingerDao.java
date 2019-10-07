package com.shiv.example.springdataexample.jdbc;

import java.util.List;

import javax.sql.DataSource;

public interface SingerDao {
	
	void setDataSource(DataSource ds);
	
	DataSource getDataSource();

	List<Singer> findAll();

	Singer findById(long id);
	
	List<Singer> findByFirstName(String firstName);
	
	List<Singer> findByLastName(String lastName);

	int insert(Singer singer);

	int update(Singer singer);

	int delete(Singer singer);
	
	List<Singer> findAllWithAlbums();

}
