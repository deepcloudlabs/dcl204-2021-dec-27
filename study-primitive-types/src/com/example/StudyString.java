package com.example;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@SuppressWarnings("unused")
public class StudyString {

	public static void main(String[] args) {
		// String is an immutable class like BigInteger/BigDecimal
		String name1= "Jack"; // Constant Pool -> Object Pool -> Object Cache
		String name2= new String("Jack"); // Heap String
		String name3= "Jack"; // static string
		name2 = name2.intern();
		System.err.println("name1==name2: "+(name1==name2));
		System.err.println("name1==name3: "+(name1==name3));
		
		
		name1 = name1.toUpperCase();
		System.err.println(name1);
		int p = 42; // 4-Byte
		Integer q = 42; // 12-Byte (Object Header) + 4 Byte = 16-Byte
		int x=3,y=5,z=x+y; // ALU
		Integer u=3,v=5,
				w=Integer.valueOf(u.intValue()+v.intValue());
	}

}
