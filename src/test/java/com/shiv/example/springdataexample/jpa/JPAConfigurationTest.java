package com.shiv.example.springdataexample.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = JPAConfiguration.class)
@RunWith(SpringRunner.class)
public class JPAConfigurationTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test01() {
		logger.info("TestContextLoad");
	}

}
