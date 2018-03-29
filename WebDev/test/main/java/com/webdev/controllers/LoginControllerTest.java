package com.webdev.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.webdev.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={Application.class})
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

    @Test
    public void test_login_valid() throws Exception{
        
        this.mockMvc.perform(formLogin().user("a").password("p"))
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
    }
    
}
