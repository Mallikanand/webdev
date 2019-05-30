package com.webdev.data.dao;

import java.util.List;

import com.webdev.data.model.MenuItem;

public interface MenuDAO {

	public List<MenuItem> getMenu();

	public MenuItem getMenuItemById(int id);

	public MenuItem update(MenuItem menuItem);
	
	public void saveMenuItem(MenuItem menuItem);
}
