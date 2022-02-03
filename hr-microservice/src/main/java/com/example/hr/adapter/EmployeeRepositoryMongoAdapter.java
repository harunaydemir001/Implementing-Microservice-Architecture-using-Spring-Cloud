package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentMongoRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
    private EmployeeDocumentMongoRepository mongoRepo;
    private ModelMapper mapper;
    
	public EmployeeRepositoryMongoAdapter(EmployeeDocumentMongoRepository mongoRepo, ModelMapper mapper) {
		this.mongoRepo = mongoRepo;
		this.mapper = mapper;
	}

	@Override
	public boolean existsByKimlikNo(TcKimlikNo kimlik) {
		return mongoRepo.existsById(kimlik.getValue());
	}

	@Override
	public Employee save(Employee employee) {
		var document = mapper.map(employee, EmployeeDocument.class);
		return mapper.map(mongoRepo.save(document),Employee.class);
	}

	@Override
	public Optional<Employee> remove(TcKimlikNo kimlik) {
		var document = mongoRepo.findById(kimlik.getValue());
		document.ifPresent( mongoRepo::delete);
		return document.map(doc -> mapper.map(doc, Employee.class));
	}

	@Override
	public Optional<Employee> findOneById(TcKimlikNo kimlik) {
		return mongoRepo.findById(kimlik.getValue())
				        .map(doc -> mapper.map(doc, Employee.class));
	}

}








