package com.employee.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.exception.ResourceAlreadyExitsException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import com.employee.utility.CommonConstants;
import com.employee.utility.ErrorConstant;
import com.employee.view.model.ListModal;
import com.employee.view.model.ViewEmployee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public int createEmployee(Employee employee) {

		int emailAlreadyExist = employeeRepository.chkEmailAlreadyExist(employee.getEmail());
		if (emailAlreadyExist > 0) {
			throw new ResourceAlreadyExitsException(ErrorConstant.emailIdExists);
		}
		return employeeRepository.insertEmployee(employee);
	}

	@Override
	public int deleteEmployeeById(int empId) {

		int empExist = employeeRepository.findEmployeeById(empId);
		if (CommonConstants.INT_ZERO==empExist) {
			throw new ResourceNotFoundException(ErrorConstant.emailIdNotExists);
		}

		return employeeRepository.deleteEmployeeById(empId);
	}

	@Override
	public ListModal<ViewEmployee> getEmployeeList(String searchTerm, int pageNo, int pageSize) {

		ListModal<ViewEmployee> model = new ListModal<ViewEmployee>();
		model.setPageNo(pageNo);
		model.setPageSize(pageSize);
		model.setTotalRecords(employeeRepository.getTotalEmployeeCounts(searchTerm));
		model.setData(employeeRepository.getEmployeeList(searchTerm, pageNo, pageSize));
		return model;
	}

	@Override
	public int updateEmployeeById(int empId, @Valid Employee emp) {

		int empExist = employeeRepository.findEmployeeById(empId);
		if (CommonConstants.INT_ZERO==empExist) {
			throw new ResourceNotFoundException(ErrorConstant.emailIdNotExists);
		}

		return employeeRepository.updateEmployeeById(empId, emp);

	}

}
