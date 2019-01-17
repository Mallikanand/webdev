package com.webdev.data.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.webdev.data.model.dto.MenuItemDTO;
import com.webdev.data.model.dto.OrderItemDTO;

@Entity(name="Order_Item")
public class OrderItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    private Order order; 
	
	@ManyToOne
	private MenuItem item; 
	
	@Column(name="price" ,  precision=19, scale=2, nullable=false)
	private BigDecimal price;
	
        @Column(name="quantity" , nullable=false)
        private int quantity;

	@Column(name="notes",nullable=true)
	private String notes;

        public OrderItem(){}
        public OrderItem(int id){this.id = id;}
        
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

    public OrderItem assignOrder(Order order) {
    	setOrder(order);
        return this;
	}

	public MenuItem getItem() {
		return item;
	}

	public void setItem(MenuItem item) {
		this.item = item;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
                result = prime * result + quantity;
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
		OrderItem other = (OrderItem) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (order.getId() != other.order.getId())
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
                
                
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", item=" + item + ", price=" + price + ", notes="
				+ notes + "]";
	}
	
	public OrderItemDTO toOrderItemDTO(){
		return new OrderItemDTO(this.id,this.order.getId(),this.item.toMenuItemDTO(),this.price,this.quantity,this.notes);
	}

}