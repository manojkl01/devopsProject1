package com.thbs.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thbs.models.Admin;
import com.thbs.models.User;
import com.thbs.models.house;
import com.thbs.repo.AdminRepository;
import com.thbs.repo.HouseRepository;
import com.thbs.repo.UserRepository;

import antlr.collections.List;

@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	HouseRepository houseRepository;
	

	@RequestMapping(value = "/")
	public String index() {
		return "property-detail";
	}
    
	@RequestMapping(value = "/user")
	public String user() {
		return "index";
	}
	@RequestMapping(value = "/userRegister")
	public String userRegister() {
		return "register";
	}
	@RequestMapping(value = "/about")
	public String about() {
		return "about";
	}
	
	@RequestMapping(value = "/estate_details")
	public String estate() {
		return "estate_details";
	}
	
	
	@PostMapping(value="/register")
	public String registerUser(@ModelAttribute("user") User user) {
		// TODO Auto-generated method stub
		Optional<User> searchUser = userRepository.findById(user.getUsername());
		if (searchUser.isPresent()) {
			User userFound = searchUser.get();
			return "sameusername";

		} else {
			/* user.setPassword(user.getPassword()); */
			User saveUser = userRepository.save(user);
			return "index";
		}
	}
		 
		@PostMapping("/login")
		public String loginUser(@ModelAttribute("user") User u)
		{
			Optional<User> searchUser =userRepository.findById(u.getUsername());
			 if (searchUser.isPresent())
			 { User userFromDb = searchUser.get();
			 if (u.getPassword().equals(userFromDb.getPassword()))
			 {  
			 return "estate_details";
			 } else
			 { 
				 return "invalid";
			 }
			 
			 } else return "invalid"; 
		}
	
		//admin
		
		
		 @RequestMapping(value = "/admin_options")
		 public String admin_options()
		 {
		 return "admin_options"; 
		 }
		  
		 @RequestMapping(value = "/admin_login")
		 public String admin_login() 
		 { 
			 return "admin"; 
		 }
		 
		
		
		@PostMapping("/admincheck")
		public String admin(@ModelAttribute("admin") Admin a) 
		{
			Optional<Admin> searchUser =adminRepository.findById(a.getAdminid());
			 if (searchUser.isPresent())
			 { 
				 Admin userFromDb = searchUser.get();	 
			 if (a.getPassword().equals(userFromDb.getPassword()))
			 {  
			 return "index1.html";
			 }
			 else
			 { 
				 return "admin";
			 } 
			 } 
			 else {
				 return "admin"; 
			 }
		}
	@GetMapping("/getAllHouses")	
	public String viewallhouses(@ModelAttribute("house") house h)
	{
		return null;
		
	}
		   
}