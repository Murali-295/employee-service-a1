
package com.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.entity.Employee;
import com.mk.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping("/save")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}
	
	@GetMapping("/show")
	@CircuitBreaker(name = "circuit-breaker",fallbackMethod="employeeNotFound")
	public String showPage() {

			throw new RuntimeException("CHECK!.");	
	}

	public String employeeNotFound(Exception e)
	{
		return "EmployeeNotFound";
	}
	
	@GetMapping("/id{id}")
	public Employee findEmployeeById(@PathVariable("id") Integer empId) {
		return service.findEmployeeById(empId);
	}

	@PutMapping("/salary{id}")
	public Employee salaryIncrement(@PathVariable("id") Integer empId) {
		return service.salaryIncrement(empId);
	}
}
