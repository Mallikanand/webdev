package com.webdev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value="/login")
	public String login(Model model){
		return "login";
	}
	@RequestMapping(value="/signout")
	public String logout(Model model){
		return "signout";//form based logout to make it work with CSRF
	}
}
