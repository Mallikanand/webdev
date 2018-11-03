/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webdev.annotations.DefaultTestConfig;
import com.webdev.data.model.dto.Basket;
import com.webdev.data.model.dto.BasketItem;
import com.webdev.data.model.dto.OrderDTO;
import com.webdev.services.OrderService;

/**
 *
 * @author mkotra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//@ContextConfiguration(classes={Application.class})  //////////????? TODO: Is this right?
@SpringBootTest
@DefaultTestConfig
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
        .andExpect(view().name("captureOrder"))
        .andExpect(model().attributeExists("order"));   
    }
    
    @Test
    public void test_loadOrders() throws Exception{
        
        this.mockMvc.perform(get("/orders/jsp/getOrder/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("showOrder"))
        .andExpect(model().attributeExists("order"));
    }
    
    @Test
    public void test_submitOrder() throws Exception{

    	Basket mockBasket = getBasketToSubmit();
    	
    	Gson gson = new GsonBuilder()
    			.setDateFormat("dd-MM-yyyy hh:mm:ss")
    			.create();
    	
    	String requestAsJson = gson.toJson(mockBasket);
		String responseAsJson = this.mockMvc.perform(post("/orders/saveOrder")
								    			.contentType(MediaType.APPLICATION_JSON_VALUE)
								    			.content(requestAsJson))
									    	.andExpect(status().isOk())
									    	.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
									    	.andReturn().getResponse().getContentAsString();
    	
		//System.out.println("responseAsJson: " + responseAsJson);
    	OrderDTO orderDTO = gson.fromJson(responseAsJson, OrderDTO.class);
    	    	
    	assertBasketMatchesSavedOrder(mockBasket, orderDTO);
    	
    	String savedOrderJson = this.mockMvc.perform(get("/orders/getOrder/"+orderDTO.getId()))
        			.andExpect(status().isOk())
        			.andReturn().getResponse().getContentAsString();

    	//System.out.println("savedOrderJson: " + savedOrderJson);
    	OrderDTO savedOrderDTO = gson.fromJson(savedOrderJson, OrderDTO.class);
    	assertBasketMatchesSavedOrder(mockBasket, savedOrderDTO);
    	
    	Assert.assertTrue("Response Order not same as Submitted Order.", orderDTO.equals(savedOrderDTO));  //-- This is not working due to the date fields being different ?
    }

	private void assertBasketMatchesSavedOrder(Basket basket, OrderDTO orderDTO) {
		Assert.assertTrue(orderDTO.getId() == 4); //Test In-Mem DB has only 3 orders.. so the new order should be with id 4.
    	Assert.assertTrue(orderDTO.getItems().size() == basket.getItems().length);
    	Assert.assertTrue(orderDTO.getUser().getUserId().equals(basket.getUserId()));
	}

	private Basket getBasketToSubmit() {
		Basket basket = new Basket();
		basket.setUserId("a");
    	
    	BasketItem[]  basketItems = new BasketItem[3];
    	
    	basketItems[0] = new BasketItem();
    	basketItems[1] = new BasketItem();
    	basketItems[2] = new BasketItem();
    	
    	basketItems[0].setId(1);
    	basketItems[0].setItemName("test");
    	basketItems[0].setQuantity(2);
    	basketItems[0].setItemNotes("Hello");

    	basketItems[1].setId(2);
    	basketItems[1].setItemName("test");
    	basketItems[1].setQuantity(3);
    	basketItems[1].setItemNotes("Mild");

    	basketItems[2].setId(3);
    	basketItems[2].setItemName("test");
    	basketItems[2].setQuantity(4);
    	basketItems[2].setItemNotes("Spicy");

    	basket.setItems(basketItems);
		return basket;
	}
}
