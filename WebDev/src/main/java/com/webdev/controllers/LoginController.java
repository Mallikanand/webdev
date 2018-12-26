package com.webdev.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.data.model.User;
import com.webdev.data.model.dto.LoginFormDTO;
import com.webdev.data.model.dto.UserDTO;
import com.webdev.services.UserService;

//@CrossOrigin(origins="http://localhost:3000",allowedHeaders="*")
@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;

	
/*	@RequestMapping(value="/login")
	public UserDTO login(@RequestParam("userId") String userId, @RequestParam("password") String password){
		User user = userService.getUser(userId);
		SecurityContextHolder.getContext().setAuthentication(user);
		return user.toUserDTO();
	}
	
	@RequestMapping(value="/signout")
	public String logout(Model model){
		return "signout";//form based logout to make it work with CSRF
	}
*/

	@RequestMapping(value="/login", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public UserDTO login(@RequestBody LoginFormDTO loginFormDTO){
		User user = userService.getUser(loginFormDTO.getUserId());
		if(user!= null && user.getPassword().equals(loginFormDTO.getPassword()))
			return user.toUserDTO();
		else 
			throw new IllegalArgumentException("Incorrect User Login Details");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth){
		System.out.println("inside logout handler");
		//Placeholder for doing any logout activities in the future... 
		//ie, like update user audit with details of when the user logged out , log statements, save any cart items for his reference when he logs back in next time.. 
		
	}
}
