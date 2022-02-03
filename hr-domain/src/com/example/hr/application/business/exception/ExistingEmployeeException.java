package com.example.hr.application.business.exception;

@SuppressWarnings("serial")
public class ExistingEmployeeException extends RuntimeException {

	private final String identity;

	public ExistingEmployeeException(String message, String identity) {
		super(message);
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}
	
}
