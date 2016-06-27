package com.webdev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.webdev.binding.LoginDTO;

@Controller
public class LoginController {

	/*@RequestMapping(value ="/signin", method= RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public String signin(@RequestParam(value="username",required=true) final String username, @RequestParam(value="password",required=true) final String password, Model model){

		System.out.println("Inside login controller: " + username+ " password : " + password);
		//do auth
		model.addAttribute("name",username);
		
		return "welcome"; 
	}*/
	
	/*@RequestMapping(value ="/signin", method= RequestMethod.POST)
	public String signin(@ModelAttribute final String username, @ModelAttribute final String password, Model model){

		System.out.println("Inside login controller: " + username+ " password : " + password);
		//do auth
		model.addAttribute("name",username);
		
		return "welcome"; 
	}*/
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signin(@ModelAttribute(value="login") LoginDTO login){
		
		System.out.println("email: " + login.getEmail());
		System.out.println("password: " + login.getPassword());
		
		return "welcome"; 
	}
	
	@RequestMapping(value="/login")
	public String login(Model model){
		return "login";
	}
}
