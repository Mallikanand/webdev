/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.binding;

import com.webdev.data.model.MenuItem;
import com.webdev.data.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mkotra
 */
public class OrderBean {
    
    
    public OrderBean(){}
    private List<OrderItemBean> items;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public List<OrderItemBean> getItems() {
        return items;
    }

    public void setItems(List<OrderItemBean> items) {
        this.items = items;
    }
    
    private void addItem(OrderItemBean item){
        if(items==null){
            items = new ArrayList<OrderItemBean>(); 
        }
        
        items.add(item);
    }
    
    public void addItem(MenuItem menuItem, String menuType, Integer quantity){
        addItem(new OrderItemBean(menuItem,menuType,quantity));
    }

    @Override
    public String toString() {
        return "OrderBean{items=" + items + '}';
    }
}
