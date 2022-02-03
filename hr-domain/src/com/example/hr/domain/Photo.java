package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

public final class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}
	
	public static Photo valueOf(byte[] values) {
		Objects.requireNonNull(values);
		return new Photo(values);
	}
	public static Photo valueOf(String values) {
		Objects.requireNonNull(values);
		return new Photo(Base64.getDecoder().decode(values));
	}

	public byte[] getValues() {
		return values;
	}
	
	public String getBase64Values() {
		return String.valueOf(Base64.getEncoder().encode(values));
	}
	
}
