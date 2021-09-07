package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().serializeNulls()
			.create();

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<String>(gson.toJson(employeeService.saveEmployee(employee)), HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> getAllEmployee() {
		return new ResponseEntity<String>(gson.toJson(employeeService.getAllEmployee()), HttpStatus.OK);
	}

//	produces = "application/json" and produces = {MediaType.APPLICATION_JSON_VALUE} both same

	@GetMapping(value = "/email/{emailId}", produces = "application/json")
	public ResponseEntity<String> getEmployeeByEmail(@PathVariable("emailId") String email) {
		return new ResponseEntity<String>(gson.toJson(employeeService.getEmployeeByEmail(email)), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<String> getEmployeeById(@PathVariable("id") long id) {
		return new ResponseEntity<String>(gson.toJson(employeeService.getEmployeeById(id)), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		return new ResponseEntity<String>(gson.toJson(employeeService.updateEmployee(employee, id)), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id) {
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<String>("Employee Deleted Successfully!.", HttpStatus.OK);
	}
}
