package com.webdev.services;

import java.util.List;
import java.util.Map;

import com.webdev.data.model.FoodType;
import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;

public interface MenuService {

	List<MenuItem> getAvailableMenu();

	Map<String, List<MenuItem>> getMenuItemsByMenuType();

	Map<String, List<MenuItem>> getMenuItemsByFoodType();
}
