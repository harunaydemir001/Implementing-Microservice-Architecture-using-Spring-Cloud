package com.example.lottery.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "lottery")
public interface LotteryService {
	@GetMapping("/lottery/api/v1/numbers")
	@Retry(name="lottery",fallbackMethod = "getNumbersFallback")
	@RateLimiter(name="lottery",fallbackMethod = "getNumbersFallback")
	@CircuitBreaker(name="lottery",fallbackMethod = "getNumbersFallback")
	@Bulkhead(name="lottery",fallbackMethod = "getNumbersFallback")
	List<List<Integer>> getNumbers(@RequestParam int column);
	
	default public List<List<Integer>> getNumbersFallback(
			int column,Throwable e) {
		return List.of(
			    List.of(1,2,3,4,5,6),
			    List.of(4,8,15,16,23,42)		    
			);
		}
}
