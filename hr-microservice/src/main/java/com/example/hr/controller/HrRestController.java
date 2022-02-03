package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.GetEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestScope
@RequestMapping("employees")
@CrossOrigin
@Validated
@Api( tags = "Employees")
public class HrRestController {

	private HrService hrService;

	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@GetMapping("{kimlikNo}")
	@ApiOperation(value = "This method is used to get the employee for the given {kimlikNo}")
	public GetEmployeeResponse getEmployeeByIdentity(
			@PathVariable @TcKimlikNo String kimlikNo) {
		return hrService.findEmployeeByIdentity(kimlikNo);
	}
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(
			@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}

	@DeleteMapping("{kimlikNo}")
	public FireEmployeeResponse fireEmployee(
			@PathVariable @TcKimlikNo String kimlikNo) {
		return hrService.fireEmployeeByIdentity(kimlikNo);
	}
}
