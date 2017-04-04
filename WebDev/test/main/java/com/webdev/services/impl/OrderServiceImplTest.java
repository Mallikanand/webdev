package com.webdev.services.impl;

import ch.qos.logback.core.CoreConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webdev.config.BaseConfig;
import com.webdev.data.model.Order;
import com.webdev.data.model.OrderItem;
import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseConfig.class})
@EnableTransactionManagement
public class OrderServiceImplTest {

    @Autowired
    public OrderService orderService;

    @Test
    public void test_loadOrders() {
        
        int orderId = 1;
        Order o = orderService.getOrder(orderId);
        assertOrder(o,orderId,1);
        assertItems(o);
        prettyPrint(o);
        
        orderId = 2;
        o = orderService.getOrder(orderId);
        assertOrder(o,orderId,2);
        assertItems(o);
        prettyPrint(o);
    }

    private void assertItems(Order o) {

        //assert Basic Properties.. 
        Consumer<OrderItem> validateItem = i -> {
            Assert.assertNotNull(i.getItem());
            Assert.assertNotNull(i.getPrice());
            Assert.assertNotNull(i.getItem());
            Assert.assertNotNull(i.getItem().getItemName());
        };
        o.getItems().stream().forEach(validateItem);
    
        //Assert the Order Value matches value of all Items together...
        Function<OrderItem,BigDecimal> itemValue = i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity()));
        Assert.assertTrue(o.getValue().equals(o.getItems().stream()
                                                    .map(itemValue)
                                                    .reduce(BigDecimal.ZERO, BigDecimal::add)));
    }
    
    private void assertOrder(Order o, int orderId, int itemSize) {
        Assert.assertNotNull(o);
        Assert.assertEquals(o.getId(), orderId);
        Assert.assertNotNull(o.getItems());
        Assert.assertEquals(o.getItems().size(), itemSize);
    }
    
    private void prettyPrint(Order o){
        System.out.println("PRINTING ORDER: " + o);
    }

}
