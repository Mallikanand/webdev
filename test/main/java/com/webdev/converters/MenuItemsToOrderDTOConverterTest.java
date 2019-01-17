/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.converters;

import com.webdev.binding.OrderBean;
import com.webdev.binding.OrderItemBean;
import com.webdev.config.ConverterConfiguration;
import com.webdev.data.model.MenuItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
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
@ContextConfiguration(classes={ConverterConfiguration.class})
public class MenuItemsToOrderDTOConverterTest {
    
    @Autowired
    @Qualifier("conversionService")
    private ConversionService conversionService ;// = new MenuItemsToOrderDTOConverter(); 

    public MenuItemsToOrderDTOConverterTest() {
    }

    @Test
    public void test_null_or_empty() {
        Assert.assertNull(conversionService.convert(null,OrderBean.class));
        Assert.assertNull(conversionService.convert(new HashMap<>(), OrderBean.class));
    }
    
    @Test
    public void test_valid(){
        
        Map<String,List<MenuItem>> menuItems = new HashMap<>();
        
        List<MenuItem> italianItems = new ArrayList<>();
        italianItems.add(new MenuItem("Pasta", "Italian", "FOOD", BigDecimal.ONE, "N"));
        italianItems.add(new MenuItem("Pizza", "Italian", "FOOD", BigDecimal.ONE, "N"));
        menuItems.put("Italian",italianItems);
        
        OrderBean result = conversionService.convert(menuItems,OrderBean.class);
        Assert.assertNotNull(result);
         System.out.println(result.getClass().getName());
         
//        Assert.assertTrue("result is not of type OrderDTO", result instanceof OrderDTO);
//        OrderDTO orderDTO = (OrderDTO) result;
        Assert.assertTrue("Expecting only two items",result.getItems().size()==2);
        
        List<OrderItemBean> filteredItems =  result.getItems().stream()
                .filter(item -> item.getMenuType().equals("Italian"))
                .collect(Collectors.toList());
        
        Assert.assertTrue("Found Items other than Italian", filteredItems.size()==2);

        filteredItems =  filteredItems.stream()
                .filter(item -> item.getPrice().compareTo(BigDecimal.ONE) == 0)
                .collect(Collectors.toList());
        
        Assert.assertTrue("Found Items with an invalid price", filteredItems.size()==2);

    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    
}
