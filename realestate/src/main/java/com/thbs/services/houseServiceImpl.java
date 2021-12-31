package com.thbs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thbs.models.house;
import com.thbs.repo.HouseRepository;

@Service
public class houseServiceImpl implements houseServices {

	@Autowired
	HouseRepository houseRepository;
	
	@Override
	public void saveEmployee(house house) {
		this.houseRepository.save(house);
		
	}
	@Override
	public List<house> getAllEmployees() {
		return houseRepository.findAll();
	}

	@Override
	public house getEmployeeById(int pid) {
		Optional<house> optional = houseRepository.findById(pid);
		house h = null;
		if (optional.isPresent()) {
			h = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + pid);
		}
		return h;
	}

	@Override
	public void deleteEmployeeById(int pid) {
		this.houseRepository.deleteById(pid);
		
	}

	@Override
	public Page<house> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.houseRepository.findAll(pageable);
	}

}
