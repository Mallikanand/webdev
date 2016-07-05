package com.webdev.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.data.dao.MenuDAO;
import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;
import com.webdev.services.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired 
	private MenuDAO menuDao; 
	
	@Override
	public List<MenuItem> getAvailableMenu() {
		return menuDao.getMenu();
	}

	@Override
	public Map<MenuType, List<MenuItem>> getCategorisedItems() {
		
		return getAvailableMenu()
				.stream()
				.collect(Collectors.groupingBy(MenuItem::getMenuType));
	}

}
