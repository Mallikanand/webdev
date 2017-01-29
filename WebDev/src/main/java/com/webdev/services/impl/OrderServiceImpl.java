package com.webdev.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.data.dao.OrderDao;
import com.webdev.data.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao; 
	
	@Override
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

}
