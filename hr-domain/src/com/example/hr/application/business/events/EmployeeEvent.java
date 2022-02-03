package com.example.hr.application.business.events;

import java.util.UUID;

public class EmployeeEvent {
	private final String eventId;

	public EmployeeEvent() {
		this.eventId = UUID.randomUUID().toString();
	}

	public String getEventId() {
		return eventId;
	}
}
