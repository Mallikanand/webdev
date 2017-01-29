package com.webdev.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webdev.config.BaseConfig;
import com.webdev.data.model.Order;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BaseConfig.class})
public class OrderServiceImplTest {

	@Autowired
	public OrderService orderService;
	
	@Test
	public void test_loadOrders(){
		Order o = orderService.getOrder(1);
		
		Assert.assertNotNull(o);
		Assert.assertEquals(o.getId(), 1);
		Assert.assertNotNull(o.getItems());
		Assert.assertEquals(o.getItems().size(), 1);
		
		o.getItems().stream().forEach(i -> {Assert.assertNotNull(i.getItem());
											Assert.assertNotNull(i.getPrice());
											});
	}
	
	
}
