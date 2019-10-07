package com.shiv.example.springdataexample.jdbc;

import java.util.List;

public interface SingerDao {

	List<Singer> findAll();

	Singer findByName(String name);

	void insert(Singer singer);

	void update(Singer singer);

	void delete(Singer singer);

}
