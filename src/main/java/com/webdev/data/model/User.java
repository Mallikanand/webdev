package com.webdev.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.webdev.data.model.dto.UserDTO;

@Entity(name = "Users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="user_id",length=250, unique=true, nullable=false)
    private String userId;

    @Column(name ="first_name",length=250, nullable=false)
    private String firstName;

    @Column(name ="last_name",length=250, nullable=false)
    private String lastName;

    @Column(name ="gender",length=1, nullable=false)
    private char gender;

    @Column(name ="password",length=250, nullable=false)
    private String password;

    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY)
    private Set<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    public UserDTO toUserDTO(){
    	return new UserDTO(this.id,this.userId,this.firstName,this.lastName,this.gender);
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

