package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.GetEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee, GetEmployeeResponse>
	    EMPLOYEE_TO_GET_EMPLOYEE_RESPONSE_CONVERTER = 
	(context) -> {
	   var employee = context.getSource();
	   var fullname = employee.getFullname();
	   var response = new GetEmployeeResponse();
	   response.setIdentity(employee.getTcKimlikNo().getValue());
	   response.setFirstName(fullname.getFirst());
	   response.setLastName(fullname.getLast());
	   response.setIban(employee.getIban().getValue());
	   response.setSalary(employee.getSalary().getValue());
	   response.setPhoto(employee.getPhoto().getBase64Values());
	   response.setJobStyle(employee.getJobStyle());
	   response.setDepartments(employee.getDepartments());
	   response.setBirthYear(employee.getBirthYear().getValue());
	   System.err.println("EMPLOYEE_TO_GET_EMPLOYEE_RESPONSE_CONVERTER: "+response);
	   return response;
	};
	private static final Converter<Employee, FireEmployeeResponse>
	EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER = 
	(context) -> {
		var employee = context.getSource();
		var fullname = employee.getFullname();
		var response = new FireEmployeeResponse();
		response.setIdentity(employee.getTcKimlikNo().getValue());
		response.setFullname(String.format("%s %s", 
				fullname.getFirst(),fullname.getLast()));
		response.setIban(employee.getIban().getValue());
		response.setSalary(employee.getSalary().getValue());
		response.setJobStyle(employee.getJobStyle().name());
		response.setBirthYear(employee.getBirthYear().getValue());
		return response;
	};
	
	private static final Converter<Employee, HireEmployeeResponse>
	EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER = 
	(context) -> {
		var employee = context.getSource();
		var fullname = employee.getFullname();
		var response = new HireEmployeeResponse();
		response.setIdentity(employee.getTcKimlikNo().getValue());
		response.setFullname(String.format("%s %s", 
				fullname.getFirst(),fullname.getLast()));
		return response;
	};
	
	private static final Converter<HireEmployeeRequest, Employee>
	HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = 
	(context) -> {
		var request = context.getSource();
		return new Employee.Builder()
				           .tcKimlikNo(request.getIdentity())
				           .fullname(request.getFirstName(), request.getLastName())
				           .iban(request.getIban())
				           .salary(request.getSalary())
				           .birthYear(request.getBirthYear())
				           .photo(request.getPhoto())
				           .jobStyle(request.getJobStyle())
				           .departments(request.getDepartments().toArray(new String[0]))
				           .build();
	};
	
	private static final Converter<EmployeeDocument, Employee>
	EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER = 
	(context) -> {
		var document = context.getSource();
		var names = document.getFullname().split(" ");
		return new Employee.Builder()
				.tcKimlikNo(document.getIdentity())
				.fullname(names[0], names[1])
				.iban(document.getIban())
				.salary(document.getSalary())
				.birthYear(document.getBirthYear())
				.photo(document.getPhoto().getBytes())
				.jobStyle(document.getJobStyle().name())
				.departments(document.getDepartments().toArray(new String[0]))
				.build();
	};

	private static final Converter<Employee, EmployeeDocument>
	EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER = 
	(context) -> {
		var employee = context.getSource();
		var fullname = employee.getFullname();
		var empDoc = new EmployeeDocument();
		empDoc.setIdentity(employee.getTcKimlikNo().getValue());
		empDoc.setFullname(String.format("%s %s", 
				fullname.getFirst(),fullname.getLast()));
		empDoc.setIban(employee.getIban().getValue());
		empDoc.setSalary(employee.getSalary().getValue());
		empDoc.setJobStyle(employee.getJobStyle());
		empDoc.setBirthYear(employee.getBirthYear().getValue());
		empDoc.setPhoto(employee.getPhoto().getBase64Values());
		empDoc.setDepartments(
				employee.getDepartments()
				        .stream()
				        .map(Department::name).toList());
		return empDoc;
	};

	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER, 
				Employee.class, EmployeeDocument.class);
		mapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER, 
				EmployeeDocument.class, Employee.class);
		mapper.addConverter(EMPLOYEE_TO_GET_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, GetEmployeeResponse.class);
		mapper.addConverter(EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, FireEmployeeResponse.class);
		mapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, HireEmployeeResponse.class);
		mapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, 
				HireEmployeeRequest.class, Employee.class);
		return mapper;
	}
}
