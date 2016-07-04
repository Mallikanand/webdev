package com.webdev.services;

import java.util.List;
import java.util.Map;

import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;

public interface MenuService {

	public List<MenuItem> getAvailableMenu();

	public Map<MenuType, MenuItem> getCategorisedItems();
}
