package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.example.banking.domain.exception.InsufficientBalanceException;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
class AccountTest {

	@Test
	@DisplayName("create account object with iban and balance successfuly")
	void createObjectWithIbanAndBalanceSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var acc = new Account("tr1", 10_000);
		// 3. Verification
		assertEquals("tr1", acc.getIban());
		assertEquals(10_000, acc.getBalance());
		// 4. Tear-down
	}

	@Test
	@DisplayName("create account object with iban, balance, and status successfuly")
	void createObjectWithIbanBalanceAndStatusSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var acc = new Account("tr1", 10_000, AccountStatus.BLOCKED);
		// 3. Verification
		assertEquals("tr1", acc.getIban());
		assertEquals(10_000, acc.getBalance());
		assertEquals(AccountStatus.BLOCKED, acc.getStatus());
		// 4. Tear-down
	}

	@Test
	@DisplayName("set account status to CLOSED successfuly")
	void setAccountStatusSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var acc = new Account("tr1", 10_000, AccountStatus.ACTIVE);
		acc.setStatus(AccountStatus.CLOSED);
		// 3. Verification
		assertEquals("tr1", acc.getIban());
		assertEquals(10_000, acc.getBalance());
		assertEquals(AccountStatus.CLOSED, acc.getStatus());
		assertEquals("ACTIVE", AccountStatus.ACTIVE.name());
		assertEquals("CLOSED", AccountStatus.CLOSED.name());
		assertEquals("BLOCKED", AccountStatus.BLOCKED.name());
		assertEquals(0, AccountStatus.ACTIVE.ordinal());
		assertEquals(1, AccountStatus.CLOSED.ordinal());
		assertEquals(2, AccountStatus.BLOCKED.ordinal());
		// 4. Tear-down
	}

	@ParameterizedTest
	@CsvFileSource(resources = "deposit-fails.csv")
	@DisplayName("deposit negative amount to an accout should fail")
	void depositWithNegativeAmountShouldFail(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance);
		// 2. Call exercise method
		assertThrows(IllegalArgumentException.class, () -> acc.deposit(amount));
		// 3. verification
		assertEquals(balance, acc.getBalance(), 0.001);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-fails.csv")
	@DisplayName("withdraw negative amount from an accout should fail")
	void withdrawWithNegativeAmountShouldFail(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance);
		// 2. Call exercise method
		if (amount <= 0)
			assertThrows(IllegalArgumentException.class, () -> acc.withdraw(amount));
		else
			assertThrows(InsufficientBalanceException.class, () -> acc.withdraw(amount));
		// 3. verification
		assertEquals(balance, acc.getBalance());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-all.csv")
	@DisplayName("withdraw all balance from an accout should success")
	void withdrawAllAmountShouldSuccess(String iban, double balance) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance);
		// 2. Call exercise method
		var allBalance = acc.withdrawAll();
		// 3. verification
		assertEquals(allBalance, balance);
		assertEquals(0, acc.getBalance());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-success.csv")
	@DisplayName("withdraw amount under balance from an accout should success")
	void withdrawUnderBalanceShouldSuccess(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance);
		// 2. Call exercise method
		acc.withdraw(amount);
		// 3. verification
		assertEquals(balance - amount, acc.getBalance());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "withdraw-fails.csv")
	@DisplayName("withdraw negative amount or over balance from an accout should fail")
	void withdrawUnderNegativeAmountShouldFail(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance);
		// 2. Call exercise method
		if (amount <= 0)
			assertThrows(IllegalArgumentException.class, () -> acc.withdraw(amount));
		else
			assertThrows(InsufficientBalanceException.class, () -> acc.withdraw(amount));
		// 3. verification
		assertEquals(balance, acc.getBalance());
	}

	@ParameterizedTest
	@CsvFileSource(resources = "deposit-success.csv")
	@DisplayName("deposit positive amount to an accout should success")
	void depositWithPositiveAmountShouldSuccess(String iban, double balance, double amount) throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account(iban, balance);
		// 2. Call exercise method
		acc.deposit(amount);
		// 3. verification
		assertEquals(balance + amount, acc.getBalance());
	}

	@Test
	void toStringShouldStartWithAccount() throws Exception {
		// 1. Test Fixture/Setup
		var acc = new Account("tr1", 10_000);
		// 2. Call exercise method
		// 3. verification
		assertTrue(acc.toString().startsWith("Account"));
	}
}
