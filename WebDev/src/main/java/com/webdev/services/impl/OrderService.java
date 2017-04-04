package com.webdev.services.impl;

import com.webdev.data.model.Order;

public interface OrderService {

    public Order getOrder(int i);
    public void save(Order order);

}
