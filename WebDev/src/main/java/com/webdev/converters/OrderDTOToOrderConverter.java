/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.converters;

import com.webdev.binding.OrderBean;
import com.webdev.binding.OrderItemBean;
import com.webdev.data.model.Order;
import com.webdev.data.model.OrderItem;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author mkotra
 */
public class OrderDTOToOrderConverter implements Converter<OrderBean,Order> {

    private Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    
    public OrderDTOToOrderConverter() {
    }

    @Override
    public Order convert(OrderBean source) {
        LOG.info("About to place an order with following Items: " + source);
                
        Order order = createBlankOrder();
        Set<OrderItem> items = getOrderItems(source, order);

        LOG.info("No. of OrderItems after converting OrderBean.getItems: {} " , items.size());
        
        BigDecimal orderValue = calculateOrderValue(items);

        order.setItems(items);
        order.setValue(orderValue);
        order.setUser(source.getUser());
        
        LOG.info("order Value: {}", orderValue);
        
        return order;

    }
    
    private Order createBlankOrder(){

        Order order = new Order();
            order.setPlacementDate(new Date());
            order.setDeliveryDate(new Date());
            order.setStatus('P');

        return order;
    }
    
    private Set<OrderItem> getOrderItems(OrderBean orderBean, Order order){
        if(orderBean == null || orderBean.getItems() ==null || orderBean.getItems().isEmpty()) 
            return new HashSet<>();
        
        return orderBean.getItems().stream()
                .filter(Objects::nonNull)
                .filter(itemBean -> itemBean.getQuantity() > 0 ) 
                .map(OrderItemBean::toOrderItem)
                .map(item -> {
                                item.setOrder(order);
                                return item;
                            })
                .collect(Collectors.toSet());
    }
    
    private BigDecimal calculateOrderValue(Set<OrderItem> items){
        
        if(items==null) return BigDecimal.ZERO;
        
        return items.stream()
            .filter(Objects::nonNull)
            .peek(orderItem     -> LOG.info("itemName: {} , itemPrice:{}, quantity:{}",orderItem.getId(),orderItem.getPrice(),orderItem.getQuantity()))
            .filter(orderItem   -> orderItem.getPrice()!= null &&
                                   BigDecimal.ZERO.compareTo(orderItem.getPrice()) < 0 )
            .filter(orderItem   -> orderItem.getQuantity() >= 1 )
            .peek(orderItem     -> LOG.info("OrderItemValue : {}", orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))))
            .map(orderItem      -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    
}
