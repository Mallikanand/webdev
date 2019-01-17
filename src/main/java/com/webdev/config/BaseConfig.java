package com.webdev.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.webdev.data.util.hibernate.HibernateConfig;

@Import({DBConfiguration.class,HibernateConfig.class,ConverterConfiguration.class})
@ComponentScan(basePackages={"com.webdev.data.dao","com.webdev.services"})
public class BaseConfig {
	//hopefully much more to follow here.. .

}
