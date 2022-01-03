package com.example.banking.domain;

import com.example.banking.domain.exception.InsufficientBalanceException;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Account extends Object {
	// Notes:
	// iban is an attribute/state/property/data
	// iban is an instance and reference variable
	// iban is automatically initialized to null
	private final String iban;

	// Notes:
	// balance is an instance and value-typed variable
	// balance is automatically initialized to 0.0
	protected double balance;
	private AccountStatus status = AccountStatus.ACTIVE;

	// Notes:
	// Constructor is a special method used to initialize the object state
	// new always triggers a Constructor
	// Constructor cannot return a value!
	public Account(String iban, double balance) {
		this.iban = iban;
		this.balance = balance;
	}

	public Account(String iban, double balance, AccountStatus status) {
		this.iban = iban;
		this.balance = balance;
		this.status = status;
	}

	public String getIban() {
		return this.iban;
	}

	public double getBalance() {
		return this.balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	// Notes:
	// deposit is a business method
	// returning a boolean value to refer whether the method is successful or not
	// is generally not a good idea!
	// we will give a better solution later on the course.
	public double deposit(double amount) {
		// validation
		if (amount <= 0)
			throw new IllegalArgumentException(
					"Amount must be positive.");
		// business logic
		this.balance = this.balance + amount;
		return balance;
	}

	// withdraw is a business method
	public double withdraw(double amount) throws InsufficientBalanceException {
		System.out.println("Account::withdraw");
		// validation
		if (amount <= 0)
			throw new IllegalArgumentException(
					"Amount must be positive.");
		// business rule
		if (amount > this.balance)
			throw new InsufficientBalanceException(
			   "Your balance does not cover your expenses",
			   amount-balance
			);
		// this.balance = this.balance - amount;
		this.balance -= amount;
		return balance;
	}

	public double withdrawAll() {
		var balance = this.balance;
		this.balance = 0.0;
		return balance;
	}

	// toString is a utility method
	// its aim is to produce a string representation of the object
	@Override
	public String toString() {
		return "Account [iban=" + iban + ", balance=" + balance + ", status=" + status + "]";
	}

}