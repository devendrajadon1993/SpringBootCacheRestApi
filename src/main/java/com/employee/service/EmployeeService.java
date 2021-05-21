package com.employee.service;

import javax.validation.Valid;

import com.employee.entity.Employee;
import com.employee.view.model.ListModal;
import com.employee.view.model.ViewEmployee;

public interface EmployeeService {

	int createEmployee(Employee employee);

	int deleteEmployeeById(int empId);

	ListModal<ViewEmployee> getEmployeeList(String searchTerm, int pageNo, int pageSize);

	int updateEmployeeById(int empId, @Valid Employee emp);

}
