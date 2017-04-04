/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.controllers;

import com.webdev.binding.OrderDTO;
import com.webdev.binding.OrderItemBean;
import com.webdev.data.model.Order;
import com.webdev.data.model.OrderItem;
import com.webdev.services.impl.OrderService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mkotra
 */
@Controller
@RequestMapping("/orders/")
public class OrderController {
    
    Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private OrderService orderService;
    
    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService;
    
    @RequestMapping(value = "captureOrder", method = RequestMethod.POST)
    public ModelAndView processOrder(@ModelAttribute("order") OrderDTO orderBean, Map<String, Object> model){
        
        LOG.info("Order Bean is: {} ", orderBean);
        
        Order order = conversionService.convert(orderBean,Order.class);
        orderService.save(order);
        
        LOG.info("order Saved successfully .. , order Id: {}" , order.getId());
        
        return displayOrder(order);
    }
    
    @RequestMapping(value="getOrder/{orderId}")
    public ModelAndView showOrder(@ModelAttribute("orderId") int orderId){
        
        LOG.info("Received request to getOrder for orderId: {}",orderId);
        
        Order order = orderService.getOrder(orderId);
        
        LOG.info("loaded the order: {} and attempting to display the following view: {} " , order, "showOrder");
        return displayOrder(order);
    }

    private ModelAndView displayOrder(Order order) {
        
        Map<String, Order> model = new HashMap<>();
        model.put("order", order);
        
        return new ModelAndView("showOrder",model); 
    }

    
    //////////////////////////////////////////////////////////////////////////////////
    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public Object getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

}
