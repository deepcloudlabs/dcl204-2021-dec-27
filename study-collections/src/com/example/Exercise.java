package com.example;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Exercise {

	public static void main(String[] args) {
		Set<Employee> employees = new HashSet<>();
		employees.add(new Employee("1", 10_000, "tr1"));
		employees.add(new Employee("1", 10_000, "tr1"));
		employees.add(new Employee("1", 10_000, "tr1"));
		employees.add(new Employee("2", 20_000, "tr2"));
		employees.add(new Employee("2", 20_000, "tr2"));
		employees.add(new Employee("2", 20_000, "tr2"));
		System.err.println(employees.size());
		System.err.println(
			employees.contains(new Employee("2", 12_000, "tr2"))
		);
	}

}
