package com.webdev.controllers;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdev.annotations.DefaultTestConfig;
import com.webdev.data.model.UserRoleType;
import com.webdev.data.model.dto.LoginFormDTO;
import com.webdev.data.model.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@SpringBootTest
@DefaultTestConfig
public class LoginControllerTest {
	
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
            mockMvc = MockMvcBuilders.webAppContextSetup(wac)
            		.apply(springSecurity())
            		.build();	
    }
/*
    @Test
    public void test_login_valid() throws Exception{
        
        this.mockMvc.perform(formLogin().user("userId","a").password("p"))
        .andExpect(authenticated())
        .andExpect(status().isFound());
    }
    
    @Test
    public void test_login_invalid() throws Exception{
        
        this.mockMvc.perform(formLogin().user("c").password("p"))
        .andExpect(unauthenticated())
        .andExpect(status().isFound());
    }
    
    @Test
    public void test_logout() throws Exception{
        
        this.mockMvc.perform(logout("/logout"))
        .andExpect(unauthenticated())
        .andExpect(status().isFound());
    }*/
    
    @Test
    public void test_Login() throws Exception{

    	LoginFormDTO loginFormDTO = getLoginFormDTO("a","p");
    	
    	Gson gson = new GsonBuilder()
    			.setDateFormat("dd-MM-yyyy hh:mm:ss")
    			.create();
    	
    	String requestAsJson = gson.toJson(loginFormDTO);
		String responseAsJson = this.mockMvc.perform(post("/login")
								    			.contentType(MediaType.APPLICATION_JSON_VALUE)
								    			.content(requestAsJson))
									    	.andExpect(status().isOk())
									    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
									    	.andReturn().getResponse().getContentAsString();
    	
		//System.out.println("responseAsJson: " + responseAsJson);
		UserDTO userDTO = gson.fromJson(responseAsJson, UserDTO.class);
		assertTrue(userDTO.getFirstName().equals("A"));
		Assertions.assertThat(userDTO.getRoles()).isNotEmpty().allMatch(r -> UserRoleType.USER.name().equals(r));
		Assertions.assertThat(userDTO.isAdmin()).isFalse();

		System.out.println(responseAsJson);
    }
    

    @Test
    public void test_Login_For_AdminUser() throws Exception{

    	LoginFormDTO loginFormDTO = getLoginFormDTO("admin","admin");
    	
    	Gson gson = new GsonBuilder()
    			.setDateFormat("dd-MM-yyyy hh:mm:ss")
    			.create();
    	
    	String requestAsJson = gson.toJson(loginFormDTO);
		String responseAsJson = this.mockMvc.perform(post("/login")
								    			.contentType(MediaType.APPLICATION_JSON_VALUE)
								    			.content(requestAsJson))
									    	.andExpect(status().isOk())
									    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
									    	.andReturn().getResponse().getContentAsString();
    	
		//System.out.println("responseAsJson: " + responseAsJson);
		UserDTO userDTO = gson.fromJson(responseAsJson, UserDTO.class);
		assertTrue(userDTO.getFirstName().equals("ADMIN"));
		Assertions.assertThat(userDTO.isAdmin()).isTrue();

		System.out.println(responseAsJson);
    }
    
    
    
    @Test
    public void test_logout() throws Exception{
    	this.mockMvc.perform(post("/logout"))
	    	.andExpect(status().is3xxRedirection())
	    	.andExpect(redirectedUrl("/"));
    }

	private LoginFormDTO getLoginFormDTO(String userId, String password) {
		// TODO Auto-generated method stub
		return new LoginFormDTO(userId, password);
	}

    
}
