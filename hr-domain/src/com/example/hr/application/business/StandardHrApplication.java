package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.events.EmployeeFiredEvent;
import com.example.hr.application.business.events.EmployeeHiredEvent;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private EmployeeRepository employeeRepository;
	private EventPublisher eventPublisher;

	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var kimlik = employee.getTcKimlikNo();
		if (employeeRepository.existsByKimlikNo(kimlik))
			throw new ExistingEmployeeException("Employee already exist", kimlik.getValue());
		Employee savedEmployee = employeeRepository.save(employee);
		var businessEvent = new EmployeeHiredEvent(savedEmployee);
		eventPublisher.publishEvent(businessEvent);
		return savedEmployee;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo kimlik) {
		Optional<Employee> removedEmployee = employeeRepository.remove(kimlik);
        var employee = removedEmployee.orElseThrow(() -> new EmployeeNotFoundException(
        		"Employee does not exist", kimlik.getValue()));
		eventPublisher.publishEvent(new EmployeeFiredEvent(employee));
		return employee;
	}

	@Override
	public Optional<Employee> findEmployeeByKimlikNo(TcKimlikNo kimlik) {
		return employeeRepository.findOneById(kimlik);
	}

}
