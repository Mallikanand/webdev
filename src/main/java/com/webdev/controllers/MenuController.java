package com.webdev.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.data.model.MenuItem;
import com.webdev.services.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	public MenuService getMenuService() {
		return menuService;
	}
	
    @CrossOrigin(origins="http://localhost:3000",allowedHeaders="*")
	@RequestMapping(produces="application/json")
	public Map<String, List<MenuItem>> getMenu(){
		return menuService.getMenuItemsByMenuType();
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
