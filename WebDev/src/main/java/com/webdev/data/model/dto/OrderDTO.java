package com.webdev.data.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class OrderDTO{
	private int id; 
	private Date placementDate; 
	private Date deliveryDate;
	private char status; 
	private BigDecimal value; 
	private Set<OrderItemDTO> items;
    private UserDTO user;
    
    public OrderDTO(int id, Date placementDate, Date deliveryDate,char status, BigDecimal value, Set<OrderItemDTO> items, UserDTO user){
    	this.id = id; 
    	this.placementDate = placementDate;
    	this.deliveryDate = deliveryDate; 
    	this.status = status;
    	this.value = value; 
    	this.items= items;
    	this.user = user;
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPlacementDate() {
		return placementDate;
	}

	public void setPlacementDate(Date placementDate) {
		this.placementDate = placementDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Set<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(Set<OrderItemDTO> items) {
		this.items = items;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}