package com.shiv.example.springdataexample.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = HibernateConfiguration.class)
@RunWith(SpringRunner.class)
public class SingerServiceImplTest {
	
	@Autowired
	private SingerService singerService;

	@Test
	public void test() {
		assertNotNull(singerService.findAll());
	}

}
