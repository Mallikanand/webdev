package com.webdev.services.impl;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.webdev.annotations.DefaultTestConfig;
import com.webdev.data.model.User;
import com.webdev.services.UserService;

@RunWith(SpringRunner.class)
@DefaultTestConfig
public class UserServiceImplTest {

	@Autowired
	private UserService userService; 
	
	@Test
	public void testGetUser() {
		User user = userService.getUser("a");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getFirstName(),"A");
	}

	@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditUser() {
		fail("Not yet implemented");
	}

}
