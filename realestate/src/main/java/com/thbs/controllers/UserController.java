package com.thbs.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thbs.constantProperties.Constants;
import com.thbs.models.User;
import com.thbs.repo.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;

	@PostMapping(value = Constants.USER_RGISTERATION)
	public String registerUser(@ModelAttribute("user") User user) {
		// TODO Auto-generated method stub
		Optional<User> searchUser = userRepository.findById(user.getUsername());
		if (searchUser.isPresent()) {
			User userFound = searchUser.get();
			return "sameusername";

		} else {
			userRepository.save(user);
			return "index";
		}
	}

	@PostMapping(value = Constants.USER_LOGIN_VALIDATION)
	public String loginUser(@ModelAttribute("user") User u) {
		Optional<User> searchUser = userRepository.findById(u.getUsername());
		if (searchUser.isPresent()) {
			User userFromDb = searchUser.get();
			if (u.getPassword().equals(userFromDb.getPassword())) {
				return "estate_details";
			} else {
				return "invalid";
			}

		} else
			return "invalid";
	}

}
