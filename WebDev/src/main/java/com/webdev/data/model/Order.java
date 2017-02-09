package com.webdev.data.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="Orders")
public class Order {

	@Id
	@GeneratedValue
	public int id; 

	@Temporal(TemporalType.DATE)
	@Column(name="placement_date",nullable=false)
	public Date placementDate; 
	
        @Temporal(TemporalType.DATE)
	@Column(name="delivery_date",nullable=false)
	public Date deliveryDate;
	
	@Column(name="status" , length=1 , nullable=false)
	public char status; 
	
	@Column(name="total_value",  precision=19, scale=2,nullable=false)
	public BigDecimal value; 

        @OneToMany(targetEntity = OrderItem.class, mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<OrderItem> items;

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

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

        public void addItem(OrderItem item){
            if(items==null){
                items = new HashSet<>();
            }
            
            items.add(item);
            item.setOrder(this);
        }
        
        public void removeItem(OrderItem item){
            if(items!=null){
                items.remove(item);
                item.setOrder(null);
            }
        }
        
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.placementDate);
        hash = 37 * hash + Objects.hashCode(this.deliveryDate);
        hash = 37 * hash + this.status;
        hash = 37 * hash + Objects.hashCode(this.value);
        hash = 37 * hash + Objects.hashCode(this.items);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.placementDate, other.placementDate)) {
            return false;
        }
        if (!Objects.equals(this.deliveryDate, other.deliveryDate)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", placementDate=" + placementDate + ", deliveryDate=" + deliveryDate + ", status=" + status + ", value=" + value + ", items=" + items + '}';
    }
    
    
} 
