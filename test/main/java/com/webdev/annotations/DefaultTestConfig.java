package com.webdev.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.webdev.config.TestBaseConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest(classes={TestBaseConfig.class})
@ActiveProfiles(profiles="test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public @interface DefaultTestConfig {

}
