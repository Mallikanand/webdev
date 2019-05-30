package com.webdev.services;

import java.util.List;
import java.util.Map;

import com.webdev.data.model.MenuItem;

public interface MenuService {

	List<MenuItem> getAvailableMenu();

	Map<String, List<MenuItem>> getMenuItemsByMenuType();

	Map<String, List<MenuItem>> getMenuItemsByFoodType();

	MenuItem getMenuItemById(int id);

	MenuItem update(MenuItem menuItem);
	
	void saveItem(MenuItem menuItem);

	void delete(MenuItem menuItem);
	void delete(int menuItemId);
}
