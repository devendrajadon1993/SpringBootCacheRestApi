package com.employee.exception;

public class ResourceAlreadyExitsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExitsException(String msg) {
		super(msg);
	}
}
