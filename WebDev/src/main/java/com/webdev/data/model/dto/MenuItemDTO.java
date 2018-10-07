package com.webdev.data.model.dto;

import java.math.BigDecimal;

public class MenuItemDTO{

    private int id;
    private String itemName;
    private String menuType;
    private String foodType;
    private BigDecimal price;
    private String inactive;
    
    public MenuItemDTO(int id,String itemName,String menuType,String foodType, BigDecimal price, String inactive){
    	this.id = id; 
    	this.itemName = itemName;
    	this.menuType = menuType;
    	this.foodType = foodType;
    	this.price = price;
    	this.inactive = inactive;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

}
