package com.webdev.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webdev.data.model.MenuItem;
import com.webdev.data.model.MenuType;
import com.webdev.services.MenuService;

@Controller
public class HomeController {

	@Autowired
	private	MenuService menuService; 

	/*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
     return "redirect:/home";
    }
	*/
	@RequestMapping(value="/")
	public ModelAndView home(){

		Map<String, List<MenuItem>> menuItemsByMenuType = menuService.getMenuItemsByMenuType();
		
		Map<String,Map<String, List<MenuItem>>> model = new HashMap<>();
		model.put("menuItemsByMenuType", menuItemsByMenuType);
		
		return new ModelAndView("home",model);
	}

	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
