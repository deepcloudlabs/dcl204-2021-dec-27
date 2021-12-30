package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyList {

	public static void main(String[] args) {
		// List: i) Allows duplicates ii) Ordered -> you can sort
		List<Integer> numbers = new ArrayList<>(); // LinkedList
		numbers.add(4);
		numbers.add(8);
		numbers.add(4);
		numbers.add(15);
		numbers.add(42);
		numbers.add(4);
		numbers.add(2,16);
		numbers.add(23);
		System.err.println(numbers.size());
		System.err.println(numbers);
		System.err.println(numbers.get(0));
		System.err.println(numbers.get(5));
		numbers.sort((u,v) -> v-u);
		Comparator<Integer> ascendingOrder = Integer::compare;
		var descendingOrder = ascendingOrder.reversed();
		numbers.sort(descendingOrder);
		System.err.println(numbers);
		System.err.println(numbers.contains(42));
		for (var number : numbers)
			System.out.println(number);
	}

}
