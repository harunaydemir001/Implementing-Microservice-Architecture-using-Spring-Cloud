package com.example.sc.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityCardPrinterService {

	@KafkaListener(topics = "employee-events", groupId = "security-card")
	public void listenEvents(String event) {
		System.out.println(event);
	}
}
