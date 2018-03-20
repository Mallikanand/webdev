package com.webdev.data.dao;

import com.webdev.data.model.User;

public interface UserDao {
	
	public User getUser(String userId);
	
	public void saveUser(User user);
	
	public void deleteUser(User user);
	
	public void editUser(User user);
	
}
