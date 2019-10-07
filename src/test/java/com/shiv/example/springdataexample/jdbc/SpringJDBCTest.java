package com.shiv.example.springdataexample.jdbc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.sql.DataSource;

import org.junit.BeforeClass;
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
	private SingerDao singerDao;

	@BeforeClass
	/*
	 * Tagging it with @BeforeClass as we do not want to
	 * test of the data source i not created.
	 */
	public static void test01() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfiguration.class);
		DataSource ds = ctx.getBean("dataSource",DataSource.class);
		assertNotNull(ds);
		ctx.close();
	}

}
