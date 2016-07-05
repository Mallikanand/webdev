package com.webdev.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webdev.Application;
import com.webdev.config.DBConfiguration;
import com.webdev.data.model.FoodType;
import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;
import com.webdev.services.MenuService;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DBConfiguration.class,Application.class})
public class MenuServiceImplTest {


	@Autowired
	private MenuService menuService;
	
	@Test
	public void testGetAvailableMenu() {
		List<MenuItem> items = menuService.getAvailableMenu();
		Assert.assertNotNull(items);
		Assert.assertEquals(items.size(), 5);
		System.out.println(items);
	}

	@Test
	public void testGetCategorisedItems(){
		Map<MenuType, List<MenuItem>> menuItemsByMenuType = menuService.getMenuItemsByMenuType();
		Assert.assertNotNull(menuItemsByMenuType);
		
		Map<FoodType, List<MenuItem>> menuItemsByFoodType = menuService.getMenuItemsByFoodType();
		Assert.assertNotNull(menuItemsByFoodType);
		
	}
	
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
