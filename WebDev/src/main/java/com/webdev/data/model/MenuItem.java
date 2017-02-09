package com.webdev.data.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//@TypeDef(name="MenuType",typeClass=MenuType.class)
@Entity
@Table(name="MenuItem")
public class MenuItem {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="item_name" , length=50)
	private String itemName;
	
	/*@Type(type="org.hibernate.type.EnumType", 
		parameters={
			@Parameter (name="enumClass",value="com.webdev.data.model.MenuType"),
			@Parameter (name="useNamed",value="true"),
			@Parameter (name="type",value="12")
			})*/
	
	//TODO: change the type to MenuType Enum
	@Column(name="menu_type", length=15)
	private String menuType;
	
	//TODO: change the type to FoodType Enum
	@Column(name="food_type", length=15)
	private String foodType;

	@Column(name="price", precision=19, scale=2)
	private BigDecimal price;
	
	@Column(name="inactive", length=1)
	private String inactive;
	
	/**The default constructor is a MUST for Hibernate to Transform the resultset to this Bean**/
	public MenuItem(){}
	
	public MenuItem(String itemName, String menuType, String foodType, BigDecimal price, String inactive){
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
}
