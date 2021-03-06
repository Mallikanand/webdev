/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.converters;

import com.webdev.binding.OrderBean;
import com.webdev.data.model.MenuItem;
import java.util.List;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author mkotra
 */
public class MenuItemsToOrderDTOConverter implements Converter<Map<String,List<MenuItem>>, OrderBean>{

    @Override
    public OrderBean convert(Map<String,List<MenuItem>> source) {
        if(source == null || source.isEmpty())
            return null;
        
        OrderBean order = new OrderBean();

        source.entrySet().forEach((entry) -> {
            String menuType = entry.getKey();
            List<MenuItem> mappedItems = entry.getValue();

            mappedItems.forEach((item) -> {
                order.addItem(item, menuType, 0);
            });
        });
        
        return order; 
    }
    
}
