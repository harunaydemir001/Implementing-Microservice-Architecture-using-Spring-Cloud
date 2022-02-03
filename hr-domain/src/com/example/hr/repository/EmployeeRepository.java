package com.example.hr.repository;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface EmployeeRepository {

	boolean existsByKimlikNo(TcKimlikNo kimlik);

	Employee save(Employee employee);

	Optional<Employee> remove(TcKimlikNo kimlik);

	Optional<Employee> findOneById(TcKimlikNo kimlik);

}
