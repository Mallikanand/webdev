package com.webdev.data.dao;

import java.util.Set;

import com.webdev.data.model.Order;

public interface OrderDao {

    public Order getOrder(int orderId);
    public void save(Order order);
    public Set<Order> getOrdersByUser(String userId);
	Set<Order> getAllOrders();
}
