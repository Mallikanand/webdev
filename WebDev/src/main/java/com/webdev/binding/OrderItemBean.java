/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.binding;

import com.webdev.data.model.MenuItem;
import com.webdev.data.model.OrderItem;
import java.math.BigDecimal;

/**
 *
 * @author mkotra
 */
public class OrderItemBean{
    private String menuType; 
    private Integer quantity;

    private int itemId;
    private String itemName;
    private BigDecimal price;

    public OrderItemBean(MenuItem menuItem, String menuType, Integer quantity) {
        this.itemId = menuItem.getId();
        this.itemName = menuItem.getItemName();
        this.price = menuItem.getPrice();
        
        this.menuType = menuType;
        this.quantity = quantity;
    }

    public OrderItemBean() {
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public OrderItem toOrderItem(){
        OrderItem orderItem = new OrderItem();
        
        MenuItem menuItem = new MenuItem(this.itemId);
        menuItem.setItemName(itemName);
        
        orderItem.setItem(menuItem);
        orderItem.setPrice(this.price);
        orderItem.setQuantity(this.quantity);
        return orderItem;
    }

    @Override
    public String toString() {
        return "OrderItemBean{" + "menuType=" + menuType + ", quantity=" + quantity + ", itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + '}';
    }
}
