package com.webdev.services.impl;

import java.util.List;

import com.webdev.data.model.Order;
import java.util.Set;

public interface OrderService {

    public Order getOrder(int i);
    public void save(Order order);
	public Set<Order> getOrdersByUser(String userId);

}
