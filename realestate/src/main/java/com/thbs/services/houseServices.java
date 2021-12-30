package com.thbs.services;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.thbs.models.house;

import antlr.collections.List;

@Service
public interface houseServices  {

	java.util.List<house> getAllEmployees();
	void saveEmployee(house h);

	house getEmployeeById(int pid);

	void deleteEmployeeById(int pid);
	
	Page<house> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	

}
