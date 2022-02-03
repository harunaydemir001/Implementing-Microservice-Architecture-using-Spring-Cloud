package com.example.hr.application.business.events;

import com.example.hr.domain.Employee;

public class EmployeeFiredEvent extends EmployeeEvent {
	private final Employee employee;

	public EmployeeFiredEvent(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	
}
