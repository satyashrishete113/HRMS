package com.company.employees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.employees.entity.Admin;
import com.company.employees.entity.Employee;
import com.company.employees.repository.AdminRepository;
import com.company.employees.repository.EmployeeRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repo;
	
	public void addAdmin(Admin a) {
		
		repo.save(a);
	}
	 
	public List<Admin> getAllAdmin(){
		return repo.findAll();
	}
	
	
	public void deleteAdmin(int id) {
		repo.deleteById(id);
	}
	
}
