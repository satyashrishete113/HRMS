package com.company.employees.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.employees.entity.Admin;
import com.company.employees.entity.Employee;
import com.company.employees.service.AdminService;
import com.company.employees.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/")
	public String login(Model m) {		
		return "login";
	}
	
//	@GetMapping("/validate")
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validate(Model m, @RequestParam String email, @RequestParam String password) {
		List<Admin> admins = adminService.getAllAdmin();
		for (Admin a : admins) {
			if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
				List<Employee> emp=service.getAllEmp();
				m.addAttribute("emp", emp);

				return "index";
			}
		}
//		if (email.equals("satyashrishete@gmail.com") && password.equals("mypass")) {
//			List<Employee> emp=service.getAllEmp();
//			m.addAttribute("emp", emp);
//
//			return "index";
//		}
		m.addAttribute("error", "Invalid credentials.");
		return "login";
	}

	@GetMapping("/home")
	public String home(Model m) {
		
		List<Employee> emp=service.getAllEmp();
		m.addAttribute("emp", emp);
		
		return "index";
	}
	
	@GetMapping("/addemp")
	public String addEmployee() {
		return "addEmp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session) {
		
		service.addEmp(e);
		session.setAttribute("msg","Employee Added Successfully");
		return "redirect:/home";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,Model m) {
		
		Employee e=service.getEmpById(id);
		
		m.addAttribute("emp", e);
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		service.addEmp(e);
		session.setAttribute("msg", "Employee Data Updated Successfully");
		return "redirect:/home";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		
		service.deleteEmp(id);
		session.setAttribute("msg", "Employee Data Deleted Successfully");
		return "redirect:/home";
		
	}
}

/*
 <a class="btn btn-sm btn-primary" th:href="@{/edit/{id}(id=${e.id})">Edit</a>
					<a class="btn btn-sm btn-danger">Delete</a>
 
 */
