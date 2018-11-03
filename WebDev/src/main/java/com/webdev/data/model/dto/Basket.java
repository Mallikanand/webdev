package com.webdev.data.model.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A Simple DTO representing the Basket submitted by User
 * 
 * @author mkotra
 *
 */
public class Basket {

	private String userId;
	private BasketItem[] items;
	
	public Basket(){};

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BasketItem[] getItems() {
		return items;
	}

	public void setItems(BasketItem[] items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(items);
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Basket other = (Basket) obj;
		if (!Arrays.equals(items, other.items))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	// In Future this will also have other attributes like the Delivery Date ,
	// Basket Notes,

	@Override
	public String toString() {
	return "Basket [userId=" + userId + ", items=" + Arrays.toString(items) + "]";
	}
}