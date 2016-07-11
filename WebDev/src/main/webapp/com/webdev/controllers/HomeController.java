package com.webdev.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;
import com.webdev.services.MenuService;

@Controller
public class HomeController {

	@Autowired
	private	MenuService menuService; 

	@RequestMapping(value="/")
	public ModelAndView home(){

		Map<MenuType, List<MenuItem>> menuItemsByMenuType = menuService.getMenuItemsByMenuType();
		
		Map<String,Map<MenuType, List<MenuItem>>> model = new HashMap<>();
		model.put("menuItemsByMenuType", menuItemsByMenuType);
		
		return new ModelAndView("welcome",model);
	}

	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
