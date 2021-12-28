package com.example.banking.domain;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class CheckingAccount extends Account {
	private double overdraftAmount;

	public CheckingAccount(String iban, double balance, double overdraftAmount) {
		super(iban, balance);
		this.overdraftAmount = overdraftAmount;
	}

	@Override
	public boolean withdraw(double amount) {
		// validation
		if (amount<=0.0) return false;
		// business rule
		if (amount >(balance+overdraftAmount))
			return false;
		this.balance -= amount;
		return true;
	};
	
}
