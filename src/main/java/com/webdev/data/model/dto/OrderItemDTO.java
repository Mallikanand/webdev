package com.webdev.data.model.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
	private int id;
    private int orderId; 
	private MenuItemDTO menuItem; 
	private BigDecimal price;
    private int quantity;
	private String notes;
	
	public OrderItemDTO(int id,int orderId,MenuItemDTO item,BigDecimal price,int quantity,String notes){
		this.id = id;
		this.orderId = orderId;
		this.setMenuItem(item); 
		this.price = price; 
		this.quantity = quantity;
		this.notes = notes;
		

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public MenuItemDTO getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItemDTO menuItem) {
		this.menuItem = menuItem;
	}
	
}