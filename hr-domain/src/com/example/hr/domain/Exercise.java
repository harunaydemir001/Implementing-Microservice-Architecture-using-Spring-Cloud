package com.example.hr.domain;

public class Exercise {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// -Djava.lang.Integer.IntegerCache.high=4096
		Integer x = Integer.valueOf(108);
		Integer y = 108;
		Integer u = 5049;
		Integer v = 5049;
		System.err.println("x==y: "+(x==y));
		System.err.println("u==v: "+(u==v));
		var currency = FiatCurrency.valueOf("TL");
	}

}
