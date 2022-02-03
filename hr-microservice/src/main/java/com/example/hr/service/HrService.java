package com.example.hr.service;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.GetEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

public interface HrService {

	GetEmployeeResponse findEmployeeByIdentity(String kimlikNo);

	HireEmployeeResponse hireEmployee(HireEmployeeRequest request);

	FireEmployeeResponse fireEmployeeByIdentity(String kimlikNo);

}
