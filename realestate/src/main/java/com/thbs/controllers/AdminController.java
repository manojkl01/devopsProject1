package com.thbs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thbs.constantProperties.Constants;
import com.thbs.models.Admin;
import com.thbs.models.house;
import com.thbs.repo.AdminRepository;
import com.thbs.services.houseServiceImpl;

@Controller
public class AdminController {
	@Autowired
	houseServiceImpl houseService;
	@Autowired
	AdminRepository adminRepository;

	@PostMapping(value = Constants.ADMIN_LOGIN_VALIDATION)
	public String admin(@ModelAttribute("admin") Admin a, Model model) {
		Optional<Admin> searchUser = adminRepository.findById(a.getAdminid());
		if (searchUser.isPresent()) {
			Admin userFromDb = searchUser.get();
			if (a.getPassword().equals(userFromDb.getPassword())) {
				List<house> listProducts = houseService.getAllEmployees();
				model.addAttribute("listProducts", listProducts);
				return "admin_options";
			} else {
				return "admin";
			}
		} else {
			return "admin";
		}
	}

	// display list of employees
	@GetMapping(value = Constants.ADMIN_OPERATION_TESTING_PAGE)
	public String viewHomePage(Model model) {
		List<house> listProducts = houseService.getAllEmployees();
		model.addAttribute("listProducts", listProducts);
		return "index1";
	}

	@GetMapping(value = Constants.ADMIN_ADD_NEWPROPERY)
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		house house = new house();
		model.addAttribute("house", house);
		return "new_employee";
	}

	@PostMapping(value = Constants.ADMIN_SAVE_PROPERTY)
	public String saveEmployee(@ModelAttribute("house") house house) {
		// save employee to database
		houseService.saveEmployee(house);
		return "redirect:/homepage";
	}

	@GetMapping(value = Constants.ADMIN_UPDATE_PROPERTY)
	public String showFormForUpdate(@PathVariable(value = "pid") int pid, Model model) {
		house house = houseService.getEmployeeById(pid);
		model.addAttribute("house", house);
		return "update_employee";
	}

	@GetMapping(value = Constants.ADMIN_DELETE_PROPERTY)
	public String deleteEmployee(@PathVariable(value = "pid") int pid) {

		// call delete employee method
		this.houseService.deleteEmployeeById(pid);
		return "redirect:/homepage";
	}

}
