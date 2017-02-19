package com.webdev;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes ={Application.class},webEnvironment=WebEnvironment.DEFINED_PORT)
public class WelcomeJspTest {

	@Test
	public void test() {
		ResponseEntity<String> responseEntity = new TestRestTemplate().getForEntity("http://localhost:8080/", String.class);
		Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		Assert.assertTrue("Response Body: " + responseEntity.getBody(), responseEntity.getBody().contains("Available Menu"));
		
		System.out.println("responseBody: " + responseEntity.getBody());
	}

}
