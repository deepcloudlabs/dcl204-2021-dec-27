package com.example.banking.application;

import java.util.concurrent.ThreadLocalRandom;

import com.example.banking.domain.Account;
import com.example.banking.domain.CheckingAccount;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyPolymorphism {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Object o;
		o = 42;
		o = true;
		o = "Jack";
		o = new Account("tr1", 10_000);
		o = 3.1415;
		Account acc = null;
		if (ThreadLocalRandom.current().nextBoolean())
			acc = new Account("tr1", 10_000);
		else
			acc = new CheckingAccount("tr1", 10_000, 5_000);
		System.out.println(acc.getClass().getName());
		acc.withdraw(5_000);
	}

}
