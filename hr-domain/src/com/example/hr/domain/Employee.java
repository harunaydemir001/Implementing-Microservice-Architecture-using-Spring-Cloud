package com.example.hr.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Entity -> Needs to persist
//  i) Has an identity -> tcKimlikNo
// ii) Mutable 
public class Employee {
	private TcKimlikNo tcKimlikNo;
	private FullName fullname;
	private Iban iban;
	private Money salary;
	private BirthYear birthYear;
	private Photo photo;
	private JobStyle jobStyle;
	private List<Department> departments;

	public static class Builder {
		private TcKimlikNo tcKimlikNo;
		private FullName fullname;
		private Iban iban;
		private Money salary;
		private BirthYear birthYear;
		private Photo photo;
		private JobStyle jobStyle;
		private List<Department> departments;

		public Builder tcKimlikNo(String value) {
			this.tcKimlikNo = TcKimlikNo.valueOf(value);
			return this;
		}

		public Builder fullname(String first, String last) {
			this.fullname = FullName.of(first, last);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.valueOf(value);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.of(value, currency);
			return this;
		}

		public Builder salary(double value) {
			return salary(value,FiatCurrency.TL);
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.of(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.valueOf(values);
			return this;
		}

		public Builder photo(String base64Values) {
			this.photo = Photo.valueOf(base64Values);
			return this;
		}

		public Builder jobStyle(String value) {
			this.jobStyle = JobStyle.valueOf(value);
			return this;
		}

		public Builder departments(String... depts) {
			this.departments = Arrays.stream(depts).filter(Objects::nonNull).map(String::toUpperCase)
					.map(Department::valueOf).toList();
			return this;
		}

		public Employee build() {
			// Validation
			var employee = new Employee(this.tcKimlikNo);
			employee.setFullname(fullname);
			employee.setSalary(salary);
			employee.setIban(iban);
			employee.setPhoto(photo);
			employee.setJobStyle(jobStyle);
			employee.setDepartments(departments);
			employee.setBirthYear(birthYear);
			return employee;
		}

	}

	public Employee(TcKimlikNo tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public TcKimlikNo getTcKimlikNo() {
		return tcKimlikNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tcKimlikNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(tcKimlikNo, other.tcKimlikNo);
	}

	@Override
	public String toString() {
		return "Employee [tcKimlikNo=" + tcKimlikNo + ", fullname=" + fullname + ", iban=" + iban + ", salary=" + salary
				+ ", birthYear=" + birthYear + ", jobStyle=" + jobStyle + ", departments=" + departments + "]";
	}

}
