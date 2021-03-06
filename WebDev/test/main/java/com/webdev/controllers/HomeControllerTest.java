package com.webdev.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext wac; 

    private MockMvc mockMvc; 

    @Before
    public void setup(){
            mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();	
    }

    @Test
    public void testHome() throws Exception {

        this.mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("home"))
        .andExpect(model().attributeExists("order"));
    }

}
