package com.employee.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
	private int statusCode;
	private String timestamp;
	List<FieldMessage> errors = new ArrayList<>();

	public ErrorMessage(int statusCode, List<FieldMessage> errors) {
		super();
		this.statusCode = statusCode;
		this.timestamp = Instant.now().toString();
		this.errors = errors;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public List<FieldMessage> getFielderrors() {
		return errors;
	}

	public void setFielderrors(List<FieldMessage> errors) {
		this.errors = errors;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}