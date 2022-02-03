package com.example.crm.application.business.exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception {

	public CustomerNotFoundException() {
		super("Cannot find the customer");
	}



}
