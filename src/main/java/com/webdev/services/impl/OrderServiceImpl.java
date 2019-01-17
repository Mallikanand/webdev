package com.webdev.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.data.dao.OrderDao;
import com.webdev.data.model.Order;
import com.webdev.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao; 

    @Override
    public Order getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

	@Override
	public Set<Order> getOrdersByUser(String userId) {
		return orderDao.getOrdersByUser(userId);
	}

	@Override
	public Set<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}


}
