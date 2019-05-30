package com.webdev.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.webdev.annotations.DefaultTestConfig;
import com.webdev.data.model.FoodType;
import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;
import com.webdev.services.MenuService;

@RunWith(SpringRunner.class)
@DefaultTestConfig
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
	
	@Test
	public void test_update_price_for_an_menuitem() {
		List<MenuItem> availableMenu = menuService.getAvailableMenu();
		MenuItem menuItem = availableMenu.stream().findFirst().get();
		
		BigDecimal newPrice = menuItem.getPrice().add(BigDecimal.ONE);
		menuItem.setPrice(newPrice);
		
		menuService.update(menuItem);
		
		MenuItem updatedMenuItem = menuService.getMenuItemById(menuItem.getId());
		
		Assert.assertEquals(newPrice, updatedMenuItem.getPrice());
		
	}
	
	@Test
	public void test_mark_a_menuitem_as_inactive() {
		List<MenuItem> availableMenu = menuService.getAvailableMenu();
		MenuItem menuItem = availableMenu.stream().findFirst().get();
		
		menuItem.setInactive("Y");
		
		menuService.update(menuItem);
		
		MenuItem updatedMenuItem = menuService.getMenuItemById(menuItem.getId());
		
		Assertions.assertThat(updatedMenuItem.getInactive()).isEqualTo("Y");
		
	}
	
	@Test
	public void test_delete_a_menuitem() {
		List<MenuItem> availableMenu = menuService.getAvailableMenu();
		MenuItem menuItem = availableMenu.stream().findFirst().get();
		
		menuService.delete(menuItem);
		
		MenuItem deletedMenuItem = menuService.getMenuItemById(menuItem.getId());
		
		Assertions.assertThat(deletedMenuItem.getInactive()).isEqualTo("Y");
		
	}
	
	@Test
	public void test_delete_a_menuitem_By_Id() {
		List<MenuItem> availableMenu = menuService.getAvailableMenu();
		MenuItem menuItem = availableMenu.stream().findFirst().get();
		
		menuService.delete(menuItem.getId());
		
		MenuItem deletedMenuItem = menuService.getMenuItemById(menuItem.getId());
		
		Assertions.assertThat(deletedMenuItem.getInactive()).isEqualTo("Y");
		
	}
	
	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
