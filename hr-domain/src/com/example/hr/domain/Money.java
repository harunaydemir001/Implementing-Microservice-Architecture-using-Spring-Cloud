package com.example.hr.domain;

import java.util.Objects;

// Value Object
public final class Money {
	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public static Money of(double value) {
		return Money.of(value,FiatCurrency.TL);
	}
	
	public static Money of(double value, FiatCurrency currency) {
		Objects.requireNonNull(currency, "Currency cannot be null.");
		if (value <= 0.0)
			throw new IllegalArgumentException("money value cannot be zero or negative");
		return new Money(value, currency);
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", currency=" + currency + "]";
	}

}
