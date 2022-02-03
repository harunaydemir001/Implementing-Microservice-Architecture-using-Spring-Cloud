package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceConsumer {
	@Autowired private BusinessService businessService;
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		businessService.getResult().thenAccept(System.err::println);
	}
}
