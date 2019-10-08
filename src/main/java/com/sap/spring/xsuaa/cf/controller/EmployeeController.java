package com.sap.spring.xsuaa.cf.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.client.OAuth2TokenResponse;
import com.sap.cloud.security.xsuaa.test.JwtGenerator;
import com.sap.cloud.security.xsuaa.token.Token;
import com.sap.cloud.security.xsuaa.tokenflows.TokenFlowException;
import com.sap.cloud.security.xsuaa.tokenflows.XsuaaTokenFlows;
import com.sap.spring.xsuaa.cf.model.Employee;
import com.sap.spring.xsuaa.cf.repository.EmployeeRepository;


@RestController
@RequestMapping("v1")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	// Get Token Info
	@GetMapping("read/tokenInfo")
	public Token getTokenInfo(@AuthenticationPrincipal Token token) {
		return token;
	}

	// Get a particular employee by it's ID.
	@GetMapping("read/employee/{id}")
	public Optional<Employee> getEmployee(@PathVariable("id")  ObjectId  id) {
		return employeeRepository.findById(id);
	}

	// List of all employees.
	@GetMapping("read/employee/list")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// Get a count of total number of employees.
	@GetMapping("read/employee/count")
	public long getCount() {
		return employeeRepository.count();
	}

	// Delete a particular employee.
	@GetMapping("modify/employee/delete/{id}")
	public boolean deleteEmployee(@PathVariable("id") ObjectId id) {
		try {
			employeeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Add/Update a new employee.
	// @PreAuthorize("hasAuthority('Update')")
	@PostMapping("modify/employee/add")
	public boolean addEmployee(@RequestBody Employee employee) {
		try {
			employeeRepository.save(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
