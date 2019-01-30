package com.webdev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.webdev.data.util.hibernate.HibernateConfig;

@Import({TestDBConfiguration.class,HibernateConfig.class,ConverterConfiguration.class})
@ComponentScan(basePackages={"com.webdev.data.dao","com.webdev.services"})
public class TestBaseConfig {
	//hopefully much more to follow here.. .
	static{
		System.setProperty("hibernate_dialect", "org.hibernate.dialect.H2Dialect");
	}
}
