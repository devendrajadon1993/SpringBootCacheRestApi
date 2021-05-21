
package com.employee.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class Employee {

	@NotNull(message = "First Name cannot be null")
	@Size(min = 1, max = 45)
	private String firstName;
	@NotNull(message = "Last Name cannot be null")
	@Size(min = 1, max = 45)
	private String lastName;
	@NonNull
	@Email(message = "Email should be valid")
	private String email;
	@NotEmpty
	@Size(min = 10, max = 45)
	private String phoneNo;

	public Employee(String firstName, String lastName, String email, String phoneNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
