package com.webdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.webdev.config.DBConfiguration;
import com.webdev.config.MvcConfiguration;
import com.webdev.data.util.hibernate.HibernateConfig;

@SpringBootApplication
@Import({DBConfiguration.class, MvcConfiguration.class, HibernateConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}