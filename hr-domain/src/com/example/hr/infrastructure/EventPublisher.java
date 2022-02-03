package com.example.hr.infrastructure;

import com.example.hr.application.business.events.EmployeeEvent;

public interface EventPublisher {

	void publishEvent(EmployeeEvent businessEvent);

}
