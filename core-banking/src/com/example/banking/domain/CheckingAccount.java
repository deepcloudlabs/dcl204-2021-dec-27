package com.example.banking.domain;

import com.example.banking.domain.exception.InsufficientBalanceException;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class CheckingAccount extends Account {
	private double overdraftAmount;

	public CheckingAccount(String iban, double balance, double overdraftAmount) {
		super(iban, balance);
		this.overdraftAmount = overdraftAmount;
	}

	public CheckingAccount(String iban, double balance, AccountStatus status, double overdraftAmount) {
		super(iban, balance, status);
		this.overdraftAmount = overdraftAmount;
	}

	public double getOverdraftAmount() {
		return overdraftAmount;
	}

	@Override
	public double withdraw(double amount) throws InsufficientBalanceException {
		System.out.println("CheckingAccount::withdraw");
		// validation
		if (amount <= 0.0)
			throw new IllegalArgumentException(
					"Amount must be positive.");
		// business rule
		if (amount > (balance + overdraftAmount))
			throw new InsufficientBalanceException(
					   "Your balance does not cover your expenses",
					   amount-balance-overdraftAmount
					);		this.balance -= amount;
		return balance;
	}

}
