package com.employee.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.exception.ErrorMessage;
import com.employee.service.EmployeeService;
import com.employee.utility.CommonConstants;
import com.employee.view.model.ListModal;
import com.employee.view.model.ViewEmployee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Employee", description = "Employee management APIs", tags = { "Employee" })
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/v1/employee")
	@ApiOperation(value = "Add new employee", notes = "API will create new employee.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee has been created successfully."),
			@ApiResponse(code = 400, message = CommonConstants.MESSAGE_400, response = ErrorMessage.class),
			@ApiResponse(code = 500, message = CommonConstants.MESSAGE_500, response = ErrorMessage.class) })
	public void createEmployee(@Valid @RequestBody Employee emp) throws MethodArgumentNotValidException {

		LOGGER.debug("Adding a new employee entry with information: {}", emp);
		employeeService.createEmployee(emp);
		LOGGER.debug("Adding a new employee entry with information: {}", emp);
	}

	@DeleteMapping("/v1/employee/{empId}")
	@ApiOperation(value = "Deletes specified employee", notes = "Mark specified employee as deleted.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Role is successfully deleted."),
			@ApiResponse(code = 400, message = CommonConstants.MESSAGE_400, response = ErrorMessage.class),
			@ApiResponse(code = 500, message = CommonConstants.MESSAGE_500, response = ErrorMessage.class) })
	public void deleteEmployee(@PathVariable(name = "empId", required = true) int empId) {

		LOGGER.debug("Deleting a employee entry with information: {}", empId);
		int status = employeeService.deleteEmployeeById(empId);
		LOGGER.debug("Deleting a employee entry with information {}", status);
	}

	@GetMapping("/v1/employee")
	@ApiOperation(value = "Lists Employee", notes = "API will fetch employee list")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Any field level validation error OR business logic error"),
			@ApiResponse(code = 400, message = CommonConstants.MESSAGE_400, response = ErrorMessage.class),
			@ApiResponse(code = 500, message = CommonConstants.MESSAGE_500, response = ErrorMessage.class) })
	public @ResponseBody ListModal<ViewEmployee> employeeList(
			@RequestParam(name = "searchTerm", required = false, defaultValue = CommonConstants.EMPTY_STRING) String searchTerm,
			@RequestParam(name = "pageNo", required = false, defaultValue = CommonConstants.ZERO) int pageNo,
			@RequestParam(name = "pageSize", required = false, defaultValue = CommonConstants.TEN) int pageSize) {

		LOGGER.debug("Start Geting roles list with information");
		ListModal<ViewEmployee> roleList = employeeService.getEmployeeList(searchTerm, pageNo, pageSize);
		LOGGER.debug("End Geting roles list with information {} ", roleList);
		return roleList;

	}

	@PutMapping("/v1/employee/{empId}")
	@ApiOperation(value = "Update's an existing employee", notes = "API will update employee using employee id.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee has been updated successfully."),
			@ApiResponse(code = 400, message = CommonConstants.MESSAGE_400, response = ErrorMessage.class),
			@ApiResponse(code = 500, message = CommonConstants.MESSAGE_500, response = ErrorMessage.class) })
	public void updateEmployee(@PathVariable(name = "empId", required = true) int empId,
			@Valid @RequestBody Employee emp) {

		LOGGER.debug("Updating a existing employee with information: {} ", empId);
		employeeService.updateEmployeeById(empId, emp);
		LOGGER.debug("Updating a existing employee with information: {} ", empId);
	}

}
