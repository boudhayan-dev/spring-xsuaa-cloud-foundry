package com.sap.spring.xsuaa.cf.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sap.spring.xsuaa.cf.model.Employee;

public interface EmployeeRepository  extends MongoRepository<Employee, String>{

}
