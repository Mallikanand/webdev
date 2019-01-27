package com.webdev;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.webdev.annotations.DefaultTestConfig;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes ={Application.class},webEnvironment=WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DefaultTestConfig
public class WelcomeJspTest {

    @LocalServerPort
    private int randomPort; 
    
    @Test
    public void test() {
            ResponseEntity<String> responseEntity = new TestRestTemplate().getForEntity("http://localhost:"+randomPort, String.class);
            Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
            Assert.assertTrue("Response Body: " + responseEntity.getBody(), responseEntity.getBody().contains("Available Menu"));

            System.out.println("responseBody: " + responseEntity.getBody());
    }

}
