package com.example.hr.dto.response;

public class FireEmployeeResponse {
	private String identity;
	private String fullname;
	private String iban;
	private double salary;
	private String jobStyle;
	private int birthYear;

	public FireEmployeeResponse() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(String jobStyle) {
		this.jobStyle = jobStyle;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		return "FireEmployeeResponse [identity=" + identity + ", fullname=" + fullname + ", iban=" + iban + ", salary="
				+ salary + ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + "]";
	}

}
