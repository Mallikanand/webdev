/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.converters;

import com.webdev.binding.OrderBean;
import com.webdev.config.ConverterConfiguration;
import com.webdev.data.model.MenuItem;
import com.webdev.data.model.Order;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mkotra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConverterConfiguration.class})
public class OrderDTOToOrderConverterTest {
    
    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService;
    
    public OrderDTOToOrderConverterTest() {}

    @Test
    public void test_null_or_empty(){
        Assert.assertNull("Null Order Expected ... ",conversionService.convert(null, Order.class));
    }

    @Test
    public void test_valid(){
        
        OrderBean orderDTO = new OrderBean(); 
        BigDecimal pizzaPrice = new BigDecimal("35.00");
        BigDecimal pastaPrice = new BigDecimal("32.00");
        BigDecimal falafelPrice = new BigDecimal("35.00");
        
        Integer pizzaQuantity = 1;
        Integer pastaQuantity = 2;
        Integer falafelQuantity = 3;
        
        
        MenuItem pizza = new MenuItem("pizza", "Italian", "FOOD", pizzaPrice, "N");
        MenuItem pasta = new MenuItem("pasta", "Italian", "FOOD", pastaPrice, "N");
        
        MenuItem falafelWrap = new MenuItem("FalafelWrap", "Turkish", "FOOD", falafelPrice, "N");
        
        orderDTO.addItem(pizza, "Italian", pizzaQuantity);
        orderDTO.addItem(pasta, "Italian", pastaQuantity);
        orderDTO.addItem(falafelWrap, "Turkish", falafelQuantity);
        
        Assert.assertEquals(orderDTO.getItems().size(), 3);
        
        Order order = conversionService.convert(orderDTO, Order.class);
        Assert.assertNotNull(order);
        
        BigDecimal pizzaCost = pizzaPrice.multiply(new BigDecimal(pizzaQuantity));
        BigDecimal pastaCost = pastaPrice.multiply(new BigDecimal(pastaQuantity));
        BigDecimal falafelCost = falafelPrice.multiply(new BigDecimal(falafelQuantity));
        
        BigDecimal orderValue = pizzaCost.add(pastaCost).add(falafelCost);
        
        Assert.assertTrue(order.getValue().compareTo(orderValue) == 0);
       
    }
    
    public ConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
