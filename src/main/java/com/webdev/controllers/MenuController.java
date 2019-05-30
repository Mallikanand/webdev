package com.webdev.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.data.model.MenuItem;
import com.webdev.services.MenuService;

@CrossOrigin(origins="http://localhost:3000",allowedHeaders="*")
@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;

	public MenuService getMenuService() {
		return menuService;
	}
	
	@RequestMapping(value = "/menu" , produces="application/json")
	public Map<String, List<MenuItem>> getMenu(){
		return menuService.getMenuItemsByMenuType();
	}

    @RequestMapping(value = "/menu/delete/{menuItemId}", produces="application/json")
	public Map<String, List<MenuItem>> deleteItem(@ModelAttribute("menuItemId") int menuItemId){
		menuService.delete(menuItemId);
		
		return menuService.getMenuItemsByMenuType();
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
