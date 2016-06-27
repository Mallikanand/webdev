package com.webdev.services;

import java.util.List;

import com.webdev.data.model.MenuItem;

public interface MenuService {

	public List<MenuItem> getAvailableMenu();
}
