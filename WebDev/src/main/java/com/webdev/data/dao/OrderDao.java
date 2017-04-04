package com.webdev.data.dao;

import com.webdev.data.model.Order;

public interface OrderDao {

	public Order getOrder(int orderId);
        public void save(Order order);
}
