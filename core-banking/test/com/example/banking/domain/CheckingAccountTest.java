package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
class CheckingAccountTest {

	@Test
	@DisplayName("create a checking account object successfuly")
	void createObjectSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var acc = new CheckingAccount("tr1", 1_000, 200);
		// 3. Verification
		assertEquals("tr1", acc.getIban());
		assertEquals(1_000, acc.getBalance());
		assertEquals(200, acc.getOverdraftAmount());
		// 4. Tear-down
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-checking-account-fails.csv")
	@DisplayName("withdraw negative amount from a checking accout should fail")
	void withdrawWithNegativeAmountShouldFail(String iban, double balance, double overdraftAmount, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new CheckingAccount(iban, balance, overdraftAmount);
		// 2. Call exercise method
		var result = acc.withdraw(amount);
		// 3. verification
		assertFalse(result);
		assertEquals(balance, acc.getBalance());
		assertEquals(overdraftAmount, acc.getOverdraftAmount());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-checking-account-success.csv")
	@DisplayName("withdraw amount under balance and overdraftAmount from an checking accout should success")
	void withdrawUnderBalanceAndOverdraftAmountShouldSuccess(String iban, double balance,double  overdraftAmount,double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new CheckingAccount(iban, balance,overdraftAmount);
		// 2. Call exercise method
		var result = acc.withdraw(amount);
		// 3. verification
		assertTrue(result);
		assertEquals(balance - amount, acc.getBalance());
	}
	
}
