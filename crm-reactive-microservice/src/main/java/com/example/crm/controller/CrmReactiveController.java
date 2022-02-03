package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.service.ReactiveCustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CrmReactiveController {
	@Autowired
	private ReactiveCustomerService reactiveCustomerService;

	@GetMapping("{identity}")
	public Mono<CustomerResponse> findCustomerByIdentity(@PathVariable String identity) {
		return reactiveCustomerService.findById(identity);
	}
	
	@GetMapping
	public Flux<CustomerResponse> findCustomersByPage(
			@RequestParam int page,
			@RequestParam int size
			) {
		return reactiveCustomerService.findAllByPage(page,size);
	}
	
	@PostMapping
	public Mono<CustomerResponse> acquireCustomer(
			@RequestBody AcquireCustomerRequest request){
		return reactiveCustomerService.acquireCustomer(request);
	}
	
	@DeleteMapping("{identity}")
	public Mono<CustomerResponse> releaseCustomerByIdentity(@PathVariable String identity) {
		return reactiveCustomerService.releaseCustomer(identity);
	}
}
