package com.webdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.webdev.config.DBConfiguration;
import com.webdev.config.MvcConfiguration;

@SpringBootApplication
@Import({DBConfiguration.class, MvcConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}