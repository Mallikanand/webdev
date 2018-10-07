package com.webdev.data.model.dto;

public class UserDTO{
    private int id;
    private String userId;
    private String firstName;
    private String lastName;
    private char gender;

    public UserDTO(int id, String userId, String firstName, String lastName, char gender){
    	this.id = id; 
    	this.userId = userId;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.gender = gender;
    }

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
}
