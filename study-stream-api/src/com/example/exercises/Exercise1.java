package com.example.exercises;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.example.domain.Employee;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Exercise1 {

	public static void main(String[] args) {
		var employees = List.of(
				new Employee("1", 10_000, "tr1"),
				new Employee("2", 20_000, "tr2"),
				new Employee("3", 30_000, "tr3"),
				new Employee("4", 40_000, "tr4")
		);		
		var highSalEmps =
		employees.stream()
		         .filter(new Predicate<Employee>() {
					@Override
					public boolean test(Employee employee) {
						return employee.getSalary() > 20_000;
					}
				}).toList();
		highSalEmps.forEach(new Consumer<Employee>() {
			@Override
			public void accept(Employee employee) {
				System.out.println(employee);
			}
		});
	}

}

@FunctionalInterface
interface Fun {
	int haveFun();
	default boolean haveGun() {return true;}
	static void haveSun() {}
}
