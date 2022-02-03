package com.example.hr.exercises;

import com.example.hr.domain.Employee;

public class ExerciseBuilder {

	public static void main(String[] args) {
		var jack = new Employee.Builder()
				               .tcKimlikNo("11111111110")
				               .fullname("jack", "bauer")
				               .salary(10_000)
				               .iban("TR980006297276862345754253")
				               .jobStyle("FULL_TIME")
				               .birthYear(1956)
				               .departments("IT","SALES")
				               .photo("")
				               .build();
		System.err.println(jack);
	}
}
