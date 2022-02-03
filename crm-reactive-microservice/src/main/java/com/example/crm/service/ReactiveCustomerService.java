package com.example.crm.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.dto.request.AcquireCustomerRequest;
import com.example.crm.dto.response.CustomerResponse;
import com.example.crm.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService {
	@Autowired private CustomerRepository customerRepository;
	@Autowired ModelMapper modelMapper;
	public Mono<CustomerResponse> findById(String identity) {
		return customerRepository.findById(identity)
				                 .map( cust -> modelMapper.map(cust,CustomerResponse.class));
	}
	// 1. Async, 2. Functional Programming, 3. Stream Processing, 4. Observer Pattern
	public Flux<CustomerResponse> findAllByPage(int page, int size) {
		return customerRepository.findAll(PageRequest.of(page, size))
				.map(cust -> modelMapper.map(cust,CustomerResponse.class));
				
	}

	public Mono<CustomerResponse> acquireCustomer(AcquireCustomerRequest request) {
		return customerRepository.insert(modelMapper.map(request, CustomerDocument.class))
				                 .map(cust -> modelMapper.map(cust, CustomerResponse.class));
	}

	public Mono<CustomerResponse> releaseCustomer(String identity) {
		return customerRepository.deleteById(identity)
				                 .map(cust -> modelMapper.map(cust, CustomerResponse.class));
	}

}
