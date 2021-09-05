package com.employee.service;

import java.util.List;
import com.employee.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	List<Employee> getAllEmployee();
	Employee getEmployeeByEmail(String email);
	Employee getEmployeeById(long id);
	Employee updateEmployee(Employee employee,long id);
	void deleteEmployeeById(long id);
}
