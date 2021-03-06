/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.controllers;

import com.webdev.binding.OrderBean;
import com.webdev.data.model.MenuItem;
import com.webdev.data.model.Order;
import com.webdev.data.model.User;
import com.webdev.services.MenuService;
import com.webdev.services.OrderService;
import com.webdev.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService; 
    
    @Autowired
    private OrderService orderService;
    
    @Autowired 
    private	MenuService menuService; 

    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService;
    
    @RequestMapping(value = "saveOrder", method = RequestMethod.POST)
    public ModelAndView saveOrder(@ModelAttribute("order") OrderBean orderBean, Map<String, Object> model){
        
        LOG.info("Order Bean is: {} ", orderBean);
        
        User user = getAuthenticatedUser();
        orderBean.setUser(user);
        
        Order order = conversionService.convert(orderBean,Order.class);
        orderService.save(order);
        
        LOG.info("order Saved successfully .. , order Id: {}" , order.getId());
        
        return displayOrder(order);
    }

	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
        User user = userService.getUser(principal.getUsername());
		return user;
	}
    
    @RequestMapping(value = "captureOrder", method = RequestMethod.POST)
    public ModelAndView captureOrder(@ModelAttribute("order") OrderBean orderBean){        
    	Map<String, List<MenuItem>> menuItemsByMenuType = menuService.getMenuItemsByMenuType();
	    OrderBean order = conversionService.convert(menuItemsByMenuType, OrderBean.class);
	
	    Map<String,Object> model = new HashMap<>();
	    model.put("order",order );
	
	    return new ModelAndView("captureOrder",model);
	}
    
    @RequestMapping(value="getOrder/{orderId}")
    public ModelAndView getOrder(@ModelAttribute("orderId") int orderId){
        
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
    
    

}
