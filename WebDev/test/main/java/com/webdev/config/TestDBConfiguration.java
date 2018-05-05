package com.webdev.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class TestDBConfiguration {
	
	/*
	 * TODO : move this to Test .. 
	 */
	@Bean
	public DataSource datasource(){
		
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("db/schema.sql")
                    .addScript("db/data/menu.sql")
                    .addScript("db/data/users.sql")
                    .addScript("db/data/orders.sql")
                    .build();
		return db;
	}


}
