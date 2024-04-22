
package com.mk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.entity.Employee;
import com.mk.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}

	public Employee findEmployeeById(Integer empId) {
		Optional<Employee> employeedb = repository.findById(empId);

		if (employeedb == null) {
			throw new RuntimeException("Check the employee id and retry!");
		}
		Employee employee = employeedb.get();
		return employee;
	}

	public Employee salaryIncrement(Integer empId) {

		Optional<Employee> employeedb = repository.findById(empId);
		if (employeedb == null) {
			throw new RuntimeException("Check the employee id and retry!");
		}
		Employee employee = employeedb.get();
		if (employee.getEmpDepartment().equals("development") && employee.getEmpYOJ() < 2015) {
			Double empSalary = employee.getEmpSalary();
			Double increment = (empSalary * 10) / 100;
			employee.setEmpSalary(empSalary + increment);

			repository.save(employee);
			return employee;
		}

		return employee;
	}
}
