package com.webdev.data.dao;

import java.util.List;

import com.webdev.data.model.Order;
import java.util.Set;

public interface OrderDao {

    public Order getOrder(int orderId);
    public void save(Order order);
    public Set<Order> getOrdersByUser(String userId);
	Set<Order> getAllOrders();
}
