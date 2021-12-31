package com.thbs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thbs.models.house;
import com.thbs.services.houseServiceImpl;

@Controller
public class AdminController {
	@Autowired
	houseServiceImpl houseService;

	// display list of employees
	@GetMapping("/homepage")
	public String viewHomePage(Model model) {
		List<house> listProducts = houseService.getAllEmployees();
		model.addAttribute("listProducts", listProducts);
		return "index1";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		house house = new house();
		model.addAttribute("house", house);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("house") house house) {
		// save employee to database
		houseService.saveEmployee(house);
		return "redirect:/homepage";
	}

	@GetMapping("/showFormForUpdate/{pid}")
	public String showFormForUpdate(@PathVariable(value = "pid") int pid, Model model) {
		house house = houseService.getEmployeeById(pid);
		model.addAttribute("house", house);
		return "update_employee";
	}

	@GetMapping("/deleteEmployee/{pid}")
	public String deleteEmployee(@PathVariable(value = "pid") int pid) {

		// call delete employee method
		this.houseService.deleteEmployeeById(pid);
		return "redirect:/homepage";
	}

}
