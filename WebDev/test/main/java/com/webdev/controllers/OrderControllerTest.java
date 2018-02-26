/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.controllers;

import com.webdev.Application;
import com.webdev.binding.OrderItemBean;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author mkotra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={Application.class})
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    public OrderControllerTest() {
    }

    @Before
    public void setup(){
            mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();	
    }

    //TODO
    @Test
    public void test_OrderCapture() throws Exception {
        
        this.mockMvc.perform(post("/orders/captureOrder"))//.requestAttr("item1", new OrderItemBean()).requestAttr("item2", new OrderItemBean()))
        .andExpect(status().isOk())
        .andExpect(view().name("showOrder"))
        .andExpect(model().attributeExists("order"));   
    }
    
    @Test
    public void test_loadOrders() throws Exception{
        
        this.mockMvc.perform(get("/orders/getOrder/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("showOrder"))
        .andExpect(model().attributeExists("order"));
    }
}
