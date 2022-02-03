package com.example.hr.domain;

import java.util.Objects;

// Value Object
public final class FullName {
	private final String first;
	private final String last;

	private FullName(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public static FullName of(String first, String last) {
		Objects.requireNonNull(first);
		Objects.requireNonNull(last);
		if (first.length() < 2 || last.length() < 2)
			throw new IllegalArgumentException("Fullname should have at least two characters");
		return new FullName(first, last);
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	@Override
	public String toString() {
		return "FullName [first=" + first + ", last=" + last + "]";
	}

}
