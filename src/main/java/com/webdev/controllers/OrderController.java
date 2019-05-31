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
import com.webdev.data.model.dto.Basket;
import com.webdev.data.model.dto.BasketItem;
import com.webdev.data.model.dto.OrderDTO;
import com.webdev.services.MenuService;
import com.webdev.services.OrderService;
import com.webdev.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mkotra
 */
@RestController
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
    
    @RequestMapping(value = "saveOrderUsingJSP", method = RequestMethod.POST)
    public ModelAndView saveOrder(@ModelAttribute("order") OrderBean orderBean, Map<String, Object> model){
        
        LOG.info("Order Bean is: {} ", orderBean);
        
        enrichOrderBean(orderBean);
        
        Order order = conversionService.convert(orderBean,Order.class);
        orderService.save(order);
        
        LOG.info("order Saved successfully .. , order Id: {}" , order.getId());
        
        return displayOrder(order);
    }

	private void enrichOrderBean(OrderBean orderBean) {
		User user = getAuthenticatedUser();
        orderBean.setUser(user);
	}

	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
        User user = userService.getUser(principal.getUsername());
		return user;
	}
    
    @RequestMapping(value = "captureOrder", method = RequestMethod.POST)
    public ModelAndView captureOrder(){        
    	Map<String, List<MenuItem>> menuItemsByMenuType = menuService.getMenuItemsByMenuType();
	    OrderBean orderBean = conversionService.convert(menuItemsByMenuType, OrderBean.class);
	
	    Map<String,Object> model = new HashMap<>();
	    model.put("order",orderBean );
	
	    return new ModelAndView("captureOrder",model);
	}
    
    @RequestMapping(value="jsp/getOrder/{orderId}")
    public ModelAndView getOrderForJsp(@ModelAttribute("orderId") int orderId){
        
        LOG.info("Received request to getOrder for orderId: {}",orderId);
        
        Order order = orderService.getOrder(orderId);
        
        LOG.info("loaded the order: {} and attempting to display the following view: {} " , order, "showOrder");
        return displayOrder(order);
    }

    //@CrossOrigin(origins="http://localhost:3000",allowedHeaders="*")
    @RequestMapping(value="getAllOrders",produces = "application/json")
    public Set<OrderDTO> getAllOrders(){
        
        LOG.info("Received request to getAllOrders for user:");
        
        Set<Order> orders = orderService.getAllOrders();
        
        LOG.info("loaded the all orders: {} " , orders.size());
        return orders.stream()
        		.map(o -> o.toOrderDTO())
        		.collect(Collectors.toSet());
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="getAllOrders/{userId}",produces = "application/json")
    public Set<OrderDTO> getAllOrders(@ModelAttribute("userId") String userId){
        
        LOG.info("Received request to getAllOrders for user:");
        
        Set<Order> orders = orderService.getOrdersByUser(userId);
        
        
        LOG.info("loaded the all orders: {} " , orders.size());
        return orders.stream()
        		.map(o -> o.toOrderDTO())
        		.collect(Collectors.toSet());
    }
    
    //@CrossOrigin(origins="http://localhost:3000",allowedHeaders="*")
    @RequestMapping(value="saveOrder", method=RequestMethod.POST, consumes="application/json", produces="application/json")
    public OrderDTO submitOrder(@RequestBody Basket basket){
    	
    	LOG.info("received Order Request: " + basket);
    	Map<Integer, MenuItem> availabeMenu = menuService.getAvailableMenu().stream().collect(Collectors.toMap(MenuItem::getId, Function.identity()));

    	validateBasket(basket, availabeMenu);
    	
    	OrderBean orderBean = createOrderBeanForBasket(basket, availabeMenu);
    	Order order = conversionService.convert(orderBean, Order.class);
    	orderService.save(order);
    	
    	return order.toOrderDTO();
    }

	private void validateBasket(Basket basket, Map<Integer, MenuItem> availabeMenu) {
		if(basket.getUserId() == null) throw new IllegalArgumentException("User Details booking this order is necessary, may be not logged in? ");
		if(basket.getItems()==null || basket.getItems().length==0 )  throw new IllegalArgumentException("No Items in the basket ");
		
		 List<BasketItem> invalidBasketItems = Arrays.stream(basket.getItems())
			    	.filter(item -> !availabeMenu.containsKey(item.getId()))
			    	.collect(Collectors.toList());
		
		 String invalidItems = (String) Optional.ofNullable(invalidBasketItems).orElse(new ArrayList()).stream()
							    	 .map(invalidBasketItem -> ((BasketItem)invalidBasketItem).getItemName())
							    	 .collect(Collectors.joining(","));
		 
		 if(!StringUtils.isEmpty(invalidItems)) {
			 throw new IllegalArgumentException("Invalid Items found in the basket: " + invalidItems);
		 }
	}

	private OrderBean createOrderBeanForBasket(Basket basket, Map<Integer, MenuItem> availabeMenu) {
		OrderBean orderBean = new OrderBean();
    	//orderBean.setUser(getAuthenticatedUser());//TODO
    	
    	orderBean.setUser(userService.getUser(basket.getUserId()));
    	
    	 
    	Arrays.stream(basket.getItems())
    		.forEach(item -> orderBean.addItem(availabeMenu.get(item.getId()),	
    											availabeMenu.get(item.getId()).getMenuType(),
    											item.getQuantity()));
		return orderBean;
	}

    @RequestMapping(value="getOrder/{orderId}" , produces="application/json")
    public OrderDTO getOrder(@ModelAttribute("orderId") int orderId){
        
        LOG.info("Received request to getOrder for orderId: {}",orderId);
        
        Order order = orderService.getOrder(orderId);
        
        LOG.info("loaded the order: {} ", order);
        return order.toOrderDTO();
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
