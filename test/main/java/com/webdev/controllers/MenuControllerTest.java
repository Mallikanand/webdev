package com.webdev.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.webdev.annotations.DefaultTestConfig;
import com.webdev.data.model.MenuItem;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@DefaultTestConfig
public class MenuControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    
    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();	
    }

	@Test
    public void test_loadMenu() throws Exception{
        
        String menuJson = this.mockMvc.perform(get("/menu"))
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();
        
        System.out.println(menuJson);

	}
	
	@Test
	public void test_deleteMenuItem() throws Exception{
		String menuJson = this.mockMvc.perform(get("/menu"))
				.andReturn()
				.getResponse()
				.getContentAsString();
		
    	
    	Gson gson = new GsonBuilder()
    			.setDateFormat("dd-MM-yyyy hh:mm:ss")
    			.create();
    	
    	Type menuItemsMapType = new TypeToken<Map<String,List<MenuItem>>>(){}.getType();
    	Map<String,List<MenuItem>> menuItemsMap = gson.fromJson(menuJson, menuItemsMapType);

    	Assertions.assertThat(menuItemsMap).isNotEmpty();
    	
    	MenuItem anItem = menuItemsMap.values().stream()
    					.flatMap(List::stream)
    					.collect(Collectors.toList()).stream()
    					.findAny().get();
    	
    	//this.mockMvc.perform(delete("/menu/delete/"+anItem.getId())).andExpect(status().isOk());
    	
    	String newMenuJson = this.mockMvc.perform(delete("/menu/delete/"+anItem.getId()))
    			.andExpect(status().isOk())
    			.andReturn()
    			.getResponse()
    			.getContentAsString();
    	
    	Map<String,List<MenuItem>> newMenuItemsMap = gson.fromJson(newMenuJson, menuItemsMapType);
    	
    	
    	Assertions.assertThat(
    			newMenuItemsMap.values().stream()
    			.flatMap(List::stream)
    			.collect(Collectors.toList()).stream()
    			.filter(i -> ((MenuItem)i).getId() == anItem.getId())
    			.count())
    	.isEqualTo(0);
	}
	
	
}
