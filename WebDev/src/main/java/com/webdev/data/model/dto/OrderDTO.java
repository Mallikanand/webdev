package com.webdev.data.model.dto;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class OrderDTO{
	public static final String ORDER_DATE_FORMAT = "dd-MM-YYYY HH:mm";
	private int id;
	//@JsonFormat(shape=Shape.STRING,pattern=ORDER_DATE_FORMAT) //TODO: May be these date properties require custom serializer
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date placementDate; 
	//@JsonFormat(shape=Shape.STRING,pattern=ORDER_DATE_FORMAT)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((placementDate == null) ? 0 : placementDate.hashCode());
		result = prime * result + status;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		OrderDTO other = (OrderDTO) obj;
/*		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
*/		if (id != other.id)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (items.size()!= other.items.size())
			return false;
		/*else {
			boolean contentsMatch = other.items.containsAll(items) || items.containsAll(other.items);
			if(!contentsMatch)
				return false;
		}*/
/*		if (placementDate == null) {
			if (other.placementDate != null)
				return false;
		} else if (!placementDate.equals(other.placementDate))
			return false;
*/		if (status != other.status)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	static class CustomDateSerializer extends JsonSerializer<Date> {
		  
	    private SimpleDateFormat formatter 
	      = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	 
	    public CustomDateSerializer() {
	    }
	 	     
	    @Override
	    public void serialize (Date value, JsonGenerator gen, SerializerProvider provider)
	      throws IOException, JsonProcessingException {
	        gen.writeString(formatter.format(value));
	    }
	}
	
	static class CustomDateDeserializer extends JsonDeserializer<Date> {
		 
	    private SimpleDateFormat formatter = 
	      new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	 
	    public CustomDateDeserializer() {
	    }
	 
		@Override
		public Date deserialize(JsonParser jsonParser, DeserializationContext context)
				throws IOException, JsonProcessingException {
			String date = jsonParser.getText();
	        try {
	            return formatter.parse(date);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
		}
	}
}