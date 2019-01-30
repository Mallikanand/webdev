package com.webdev.data.model.dto;

public class LoginFormDTO {

	private String userId;
	private String password;
	
	public LoginFormDTO(){
		
	}
	public LoginFormDTO(String userId, String password){
		this.userId = userId;
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
