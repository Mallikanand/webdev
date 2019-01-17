package com.webdev.services;

import java.util.Set;

import com.webdev.data.model.Order;

public interface OrderService {

    public Order getOrder(int i);
    public void save(Order order);
	public Set<Order> getOrdersByUser(String userId);
	public Set<Order> getAllOrders();

}
