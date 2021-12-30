package com.example;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudySet {

	public static void main(String[] args) {
		// Set: i) Unique ii) Un-ordered -> you can't sort
		Set<Integer> numbers = new TreeSet<>((u,v)->v-u); // LinkedHashSet, TreeSet
		numbers.add(4);
		numbers.add(8);
		numbers.add(4);
		numbers.add(15);
		numbers.add(42);
		numbers.add(4);
		numbers.add(16);
		numbers.add(23);
		System.err.println(numbers.size());
		System.err.println(numbers);
		System.err.println(numbers.contains(42));
		for (var number : numbers)
			System.out.println(number);		
	}

}
