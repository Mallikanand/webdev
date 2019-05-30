package com.webdev.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "user_role")
public class UserRole {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
    @Column(name = "user_id",length=250, nullable=false)
    private String userId;
    
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleType userRole;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserRoleType getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleType userRole) {
		this.userRole = userRole;
	}
    
    

}
