package com.example.hr.application.business.events;

import com.example.hr.domain.Employee;

public class EmployeeHiredEvent extends EmployeeEvent {

	private final Employee employee;

	public EmployeeHiredEvent(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}



}
