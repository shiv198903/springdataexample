package com.shiv.example.springdataexample.jdbc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(basePackages = "com.shiv.example.springdataexample.jdbc")
public class DBConfiguration {
	
	@Bean(name = "dataSource")
	public DataSource getEmbeddedDS() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScripts("classpath:db/schema.sql",
				"classpath:db/test-data.sql").build();
	}

}
