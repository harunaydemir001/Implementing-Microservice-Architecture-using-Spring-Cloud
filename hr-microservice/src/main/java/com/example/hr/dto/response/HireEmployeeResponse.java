package com.example.hr.dto.response;

public class HireEmployeeResponse {
	private String identity;
	private String fullname;

	public HireEmployeeResponse() {
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

	@Override
	public String toString() {
		return "HireEmployeeResponse [identity=" + identity + ", fullname=" + fullname + "]";
	}

}
