package com.example.stock.domain;

public final class SellNumber {
	public static final SellNumber EMPTY = new SellNumber(0);
	private final int value;

	private SellNumber(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static SellNumber of(int value) {
		if (value < 0)
			throw new IllegalArgumentException("Sell number should be a positive value");
		return new SellNumber(value);
	}

	@Override
	public String toString() {
		return "SellNumber [value=" + value + "]";
	}

	public SellNumber add(int item) {
		return SellNumber.of(value + item);
	}

	public SellNumber subtract(int soldItems) {
		return SellNumber.of(value - soldItems);
	}

	public boolean isLessThan(SellNumber other) {
		return this.value < other.value;
	}

}
