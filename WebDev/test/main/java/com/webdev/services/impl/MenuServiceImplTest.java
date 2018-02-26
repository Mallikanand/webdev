package com.webdev.services.impl;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webdev.config.BaseConfig;
import com.webdev.data.model.FoodType;
import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;
import com.webdev.services.MenuService;
import org.springframework.test.annotation.DirtiesContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BaseConfig.class})
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
		Map<String, List<MenuItem>> menuItemsByMenuType = menuService.getMenuItemsByMenuType();
		Assert.assertNotNull(menuItemsByMenuType);
		Assert.assertEquals(2,menuItemsByMenuType.get(MenuType.ITALIAN.name()).size());
		
		Map<String, List<MenuItem>> menuItemsByFoodType = menuService.getMenuItemsByFoodType();
		Assert.assertNotNull(menuItemsByFoodType);
		Assert.assertEquals(5,menuItemsByFoodType.get(FoodType.FOOD.name()).size());
	}
	
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
