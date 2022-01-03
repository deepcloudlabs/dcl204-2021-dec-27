package com.example.banking.application;

import com.example.banking.domain.Account;
import com.example.banking.domain.exception.InsufficientBalanceException;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class BankingApp {
	public static void main(String[] args)  {
		// acc is Local and Reference Variable residing in Stack
		Account acc = // acc lives in Stack
				// new creates an Account Object in Heap
				new Account("tr1", 10_000);
		try {
			acc.withdraw(1_000);
			System.err.println(acc.getBalance());
			System.err.println(acc); // implicitly calls acc.toString()
			System.err.println(acc.toString());
		} catch (InsufficientBalanceException e) {
			System.err.println(e.getMessage());
		}
	}
}