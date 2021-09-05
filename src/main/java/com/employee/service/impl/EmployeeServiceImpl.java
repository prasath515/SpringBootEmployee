package com.employee.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRespository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRespository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRespository.findAll();
	}

	@Override
	public Employee getEmployeeByEmail(String email) {

//		Optional<Employee> employee = employeeRespository.findByEmail(email);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "Email", email);
//		}

		return employeeRespository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Email", email));
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		Employee existingEmployee = employeeRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		
		return employeeRespository.save(existingEmployee);
	}
	
	@Override
	public void deleteEmployeeById(long id) {
		// Checking employee exists 
		employeeRespository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		
		employeeRespository.deleteById(id);
		
	}
}
