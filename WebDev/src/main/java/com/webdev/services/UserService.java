package com.webdev.services;

import com.webdev.data.model.User;

public interface UserService {
	
	public User getUser(String userId);
	
	public void saveUser(User user);
	
	public void deleteUser(User user);
	
	public void editUser(User user);

}
