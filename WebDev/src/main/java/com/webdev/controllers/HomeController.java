package com.webdev.controllers;

import com.webdev.binding.OrderDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webdev.data.model.MenuItem;
import com.webdev.services.MenuService;
import org.springframework.core.convert.ConversionService;

@Controller
public class HomeController {

    @Autowired private	MenuService menuService; 
    @Autowired private ConversionService conversionService; 

    @RequestMapping(value="/")
    public ModelAndView home(){

        Map<String, List<MenuItem>> menuItemsByMenuType = menuService.getMenuItemsByMenuType();
        OrderDTO order = conversionService.convert(menuItemsByMenuType, OrderDTO.class);

        Map<String,Object> model = new HashMap<>();
        model.put("order",order );

        return new ModelAndView("home",model);
    }


    public MenuService getMenuService() {
            return menuService;
    }
    public void setMenuService(MenuService menuService) {
            this.menuService = menuService;
    }
}
