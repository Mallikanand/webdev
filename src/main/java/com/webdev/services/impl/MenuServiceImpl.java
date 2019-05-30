package com.webdev.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webdev.data.dao.MenuDAO;
import com.webdev.data.model.MenuItem;
import com.webdev.services.MenuService;

@Service
@Transactional(value="jpaTransactionManager")
public class MenuServiceImpl implements MenuService{

	@Autowired 
	private MenuDAO menuDao; 
	
	@Override
	public List<MenuItem> getAvailableMenu() {
		return menuDao.getMenu();
	}

	@Override
	public Map<String, List<MenuItem>> getMenuItemsByMenuType() {
		
		return getAvailableMenu()
				.stream()
				.collect(Collectors.groupingBy(MenuItem::getMenuType));
	}

	@Override
	public Map<String, List<MenuItem>> getMenuItemsByFoodType() {
		
		return getAvailableMenu()
				.stream()
				.collect(Collectors.groupingBy(MenuItem::getFoodType));
	}

	@Override
	public MenuItem getMenuItemById(int id) {
		return menuDao.getMenuItemById(id);
	}

	@Override
	public MenuItem update(MenuItem menuItem) {
		return menuDao.update(menuItem);
		
	}

	@Override
	public void saveItem(MenuItem menuItem) {
		menuDao.saveMenuItem(menuItem);
		
	}
	
	/**
	 * Item can never be deleted , it should be only soft-deleted as it may be part of order history for some users which will need to be displayed .. 
	 */
	@Override
	public void delete(MenuItem menuItem){
		menuItem.setInactive("Y");
		menuDao.update(menuItem);
	}

	@Override
	public void delete(int menuItemId) {
		MenuItem menuItem = menuDao.getMenuItemById(menuItemId);
		delete(menuItem);
		
	}

}
