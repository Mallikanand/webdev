/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mkotra
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes ={Application.class},webEnvironment=WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShowOrderJspTest {
  
    @LocalServerPort
    private int randomPort; 
    
    @Test
    public void showOrder_JSP() {
            ResponseEntity<String> responseEntity = new TestRestTemplate().getForEntity("http://localhost:"+randomPort+"/orders/getOrder/1", String.class);
            Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
            Assert.assertTrue("Response Body: " + responseEntity.getBody(), responseEntity.getBody().contains("Your Order"));
            Assert.assertTrue("Response Body: " + responseEntity.getBody(), responseEntity.getBody().contains("Dear a"));

            System.out.println("responseBody: " + responseEntity.getBody());
    }
}
