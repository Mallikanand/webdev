package com.webdev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value ="/login")
	public String login(@RequestParam(value="userId",required=false,defaultValue=" World") final String userId, @RequestParam(value="password",required=false,defaultValue="********") final String password, Model model){
		
		System.out.println("Inside Login method");
		model.addAttribute("name",userId);
		
		return "welcome"; 
	}
}
