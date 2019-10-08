package com.shiv.example.springdataexample.jpa;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:/persistence.properties")
@ComponentScan(basePackages = "com.shiv.example.springdataexample.jpa")
public class JPAConfiguration {

	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.format_sql}")
	private boolean format_Sql;
	@Value("${hibernate.use_sql_comments}")
	private boolean use_sql_comments;
	@Value("${hibernate.show_sql}")
	private boolean show_sql;
	@Value("${hibernate.max_fetch_depth}")
	private int max_fetch_depth;
	@Value("${hibernate.jdbc.batch_size}")
	private int jdbc_batch_size;
	@Value("${hibernate.jdbc.fetch_size}")
	private int jdbc_fetch_size;

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	private Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", dialect);
		hibernateProp.put("hibernate.format_sql", format_Sql);
		hibernateProp.put("hibernate.use_sql_comments", use_sql_comments);
		hibernateProp.put("hibernate.show_sql", show_sql);
		hibernateProp.put("hibernate.max_fetch_depth", max_fetch_depth);
		hibernateProp.put("hibernate.jdbc.batch_size", jdbc_batch_size);
		hibernateProp.put("hibernate.jdbc.fetch_size", jdbc_fetch_size);
		return hibernateProp;
	}
	
	@Bean SessionFactory hibernateSessionFactory(DataSource ds) throws IOException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(ds);
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan("com.shiv.example.springdataexample.jpa");
		sessionFactory.afterPropertiesSet();
		return sessionFactory.getObject();
	}
}
