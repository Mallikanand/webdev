package com.webdev.data.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BasketItem { // Contains Only those fields that are required to
							// place
	// this order ..

	private int id;
	private String itemName;
	private int quantity;
	private String itemNotes; // UI doesnt cater to this yet

	@JsonCreator
	public BasketItem() {
	};

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemNotes() {
		return itemNotes;
	}

	public void setItemNotes(String itemNotes) {
		this.itemNotes = itemNotes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		BasketItem other = (BasketItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasketItem [id=" + id + ", itemName=" + itemName + ", quantity=" + quantity + ", itemNotes=" + itemNotes
				+ "]";
	}
}

