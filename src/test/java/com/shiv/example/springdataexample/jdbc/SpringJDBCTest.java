package com.shiv.example.springdataexample.jdbc;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@SpringBootTest provides the basic context loader that
//loads all the classes that are to be tested.
@SpringBootTest
public class SpringJDBCTest {

	@Autowired
	private DataSource dataSource;

	@Test
	public void test01() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfiguration.class);
		DataSource dataSource = ctx.getBean("dataSource",DataSource.class);
		assertNotNull(dataSource);
	}

}
