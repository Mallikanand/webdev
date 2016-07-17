package com.webdev.data.model;

import java.math.BigDecimal;

public class MenuItem {

	private String itemName;
	private MenuType menuType;
	private FoodType foodType;
	private BigDecimal price;
	private String inactive;
	
	public MenuItem(String itemName, MenuType menuType, FoodType foodType, BigDecimal price, String inactive){
		this.itemName = itemName; 
		this.setMenuType(menuType); 
		this.foodType = foodType; 
		this.price = price; 
		this.inactive = inactive; 
		
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public MenuType getMenuType() {
		return menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
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

	@Override
	public String toString() {
		return "MenuItem [itemName=" + itemName + ", menuType=" + menuType + ", foodType=" + foodType + ", price="
				+ price + ", inactive=" + inactive + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foodType == null) ? 0 : foodType.hashCode());
		result = prime * result + ((inactive == null) ? 0 : inactive.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((getMenuType() == null) ? 0 : getMenuType().hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (foodType == null) {
			if (other.foodType != null)
				return false;
		} else if (!foodType.equals(other.foodType))
			return false;
		if (inactive == null) {
			if (other.inactive != null)
				return false;
		} else if (!inactive.equals(other.inactive))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (getMenuType() == null) {
			if (other.getMenuType() != null)
				return false;
		} else if (!getMenuType().equals(other.getMenuType()))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}


	
}
