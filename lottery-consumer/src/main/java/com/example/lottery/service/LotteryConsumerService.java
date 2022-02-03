package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryConsumerService {
	@Autowired private DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private AtomicInteger counter = new AtomicInteger();
	
	@PostConstruct
	public void init() {
		this.instances = discoveryClient.getInstances("lottery");
		instances.forEach(System.err::println);
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var rt = new RestTemplate();
		// round-robin -> client-side load balancing strategy
		var index = counter.getAndIncrement() % instances.size();
		var server = instances.get(index);
		String url = String.format(
				"http://%s:%d/lottery/api/v1/numbers?column=2",
				server.getHost(),server.getPort());
		var response = rt.getForEntity(url, String.class);
		System.out.println("LotteryConsumerService: "+response.getBody());
	}
}
