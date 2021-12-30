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
		 return findPaginated(1, "pid", "asc", model);
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		house house = new house();
		model.addAttribute("house", house);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("house") house h) {
		// save employee to database
		houseService.saveEmployee(h);
		return "redirect:/homepage";
	}
	
	@GetMapping("/showFormForUpdate/{pid}")
	public String showFormForUpdate(@PathVariable ( value = "id") int pid, Model model) {
		house h = houseService.getEmployeeById(pid);
		model.addAttribute("house", h);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{pid}")
	public String deleteEmployee(@PathVariable (value = "pid") int pid) {
		
		// call delete employee method 
		this.houseService.deleteEmployeeById(pid);
		return "redirect:/homepage";
	}
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<house> page = houseService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<house> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index1";
	}
	

}
