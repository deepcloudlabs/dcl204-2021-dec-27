package com.example;

import java.math.BigDecimal;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@SuppressWarnings("unused")
public class StudyFloatingPoints {

	public static void main(String[] args) {
		// IEEE-754
		float pi = (float) 3.141516171819; // 4-byte
		double d = 1.234; // 8-byte
		float x = 1_000_000_000;
		x = x + 50;
		System.err.printf("%12.3f", x);
		d = 2.0;
		d = d - 1.10;
		System.err.println();
		System.err.printf("%3.16f", d);
		d = 4.35;
		d = d * 100;
		System.err.println();
		System.err.printf("%3.16f", d);
		double p = (0.1 + 0.2) + 0.3;
		double q = 0.1 + (0.2 + 0.3);
		System.err.println();
		System.err.println("p==q: "+(p==q));
		BigDecimal bd = BigDecimal.valueOf(2.0);
		bd = bd.subtract(BigDecimal.valueOf(1.10));
		System.err.println("bd: "+bd);
		double one = 0/0.;
		System.err.println("one: "+one);
		System.err.println(Double.isNaN(one));
	}

}





