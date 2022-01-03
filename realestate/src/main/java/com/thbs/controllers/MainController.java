package com.thbs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.thbs.constantProperties.Constants;

@Controller
public class MainController {

	@RequestMapping(value = Constants.LANDING_PAGE)
	public String index() {
		return "property-detail";
	}

	@RequestMapping(value = Constants.CONTACT_PAGE)
	public String contact() {
		return "contact";
	}

	@RequestMapping(value = Constants.USERLOGIN_PAGE)
	public String user() {
		return "index";
	}

	@RequestMapping(value = Constants.USERREGISTER_PAGE)
	public String userRegister() {
		return "register";
	}

	@RequestMapping(value = Constants.ABOUT)
	public String about() {
		return "about";
	}

	@RequestMapping(value = Constants.ESTATEDETAILS_PAGE)
	public String estate() {
		return "estate_details";
	}

	/*
	 * Admin options
	 */

	@RequestMapping(value = Constants.ADMIN_OPERATION_PAGE)
	public String admin_options() {
		return "admin_options";
	}

	@RequestMapping(value = Constants.ADMIN_LOGIN_PAGE)
	public String admin_login() {
		return "admin";
	}
	
	@RequestMapping(value = "/update")
	public String updateProperty() {
		return "update_employee";
	}

}