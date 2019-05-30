package com.webdev.services.impl;

import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.webdev.annotations.DefaultTestConfig;
import com.webdev.data.model.User;
import com.webdev.data.model.UserRoleType;
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
	public void test_Is_Admin_User() {
		User user = userService.getUser("a");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getFirstName(),"A");
		Assertions.assertThat(user.getRoles().stream().filter( r -> r.getUserRole() == UserRoleType.ADMIN).count()).isEqualTo(0);
		
		User adminUser = userService.getUser("admin");
		Assert.assertNotNull(adminUser);
		Assert.assertEquals(adminUser.getFirstName(),"ADMIN");
		Assertions.assertThat(adminUser.getRoles().stream().filter( r -> r.getUserRole() == UserRoleType.ADMIN).count()).isEqualTo(1);
	}

	@Ignore
	@Test
	public void testSaveUser() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testEditUser() {
		fail("Not yet implemented");
	}

}
