package com.example.stock.infra;

public interface EventPublisher<Event> {
	void publishEvent(Event event);	
}
