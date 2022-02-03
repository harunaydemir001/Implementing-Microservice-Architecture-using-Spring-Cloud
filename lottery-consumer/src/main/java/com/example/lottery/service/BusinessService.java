package com.example.lottery.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class BusinessService {

	@TimeLimiter(name="lottery")
	public CompletableFuture<Integer> getResult(){
		return CompletableFuture.supplyAsync(() -> {
			try {TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3, 10));} catch (Exception e) {}
			return 42;
		});
	}
	
}
