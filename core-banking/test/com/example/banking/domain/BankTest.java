package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
class BankTest {

	@Test
	void createBankSuccessfuly() throws Throwable {
		var garantiBbva = new Bank(1, "Garanti BBVA", BankType.PRIVATE);
		assertEquals(1, garantiBbva.getId());
		assertEquals("Garanti BBVA", garantiBbva.getCommercialName());
		assertEquals(BankType.PRIVATE, garantiBbva.getType());
	}
	
	@Test
	void setCommercialNameSuccessfuly() throws Throwable {
		var garantiBbva = new Bank(1, "Garanti", BankType.PRIVATE);
		assertEquals(1, garantiBbva.getId());
		assertEquals("Garanti", garantiBbva.getCommercialName());
		garantiBbva.setCommercialName("Garanti BBVA");
		assertEquals("Garanti BBVA", garantiBbva.getCommercialName());
		assertEquals(BankType.PRIVATE, garantiBbva.getType());
	}

	@Test
	void createCustomerSuccessfuly() throws Throwable {
		var garantiBbva = new Bank(1, "Garanti BBVA", BankType.PRIVATE);
		var jack = garantiBbva.createCustomer("57487680140", "jack bauer");
		assertTrue(garantiBbva.getCustomers().contains(new Customer("57487680140", "jack bauer")));
		assertTrue(garantiBbva.findCustomerByIdentity("57487680140").isPresent());
		assertEquals(jack, garantiBbva.findCustomerByIdentity("57487680140").get());
	}

	@Test
	public void getTotalBalanceReturnsSuccessfuly() throws Throwable {
		var garantiBbva = new Bank(1, "Garanti BBVA", BankType.PRIVATE);
		var jack = garantiBbva.createCustomer("57487680140", "jack bauer");
		var kate = garantiBbva.createCustomer("80450397882", "kate austen");
		jack.addAccount(new Account("tr1", 10_000, AccountStatus.ACTIVE));
		jack.addAccount(new CheckingAccount("tr2", 20_000, AccountStatus.BLOCKED, 5_000));
		jack.addAccount(new Account("tr3", 30_000, AccountStatus.CLOSED));
		kate.addAccount(new Account("tr4", 40_000, AccountStatus.ACTIVE));
		kate.addAccount(new Account("tr5", 50_000, AccountStatus.BLOCKED));
		kate.addAccount(new CheckingAccount("tr6", 60_000, AccountStatus.CLOSED, 3_000));
		assertEquals(50_000, garantiBbva.getTotalBalance(AccountStatus.ACTIVE));
		assertEquals(70_000, garantiBbva.getTotalBalance(AccountStatus.BLOCKED));
		assertEquals(90_000, garantiBbva.getTotalBalance(AccountStatus.CLOSED));
		assertEquals(120_000, garantiBbva.getTotalBalance(AccountStatus.ACTIVE, AccountStatus.BLOCKED));
		assertEquals(140_000, garantiBbva.getTotalBalance(AccountStatus.ACTIVE, AccountStatus.CLOSED));
		assertEquals(160_000, garantiBbva.getTotalBalance(AccountStatus.BLOCKED, AccountStatus.CLOSED));
		assertEquals(210_000, garantiBbva.getTotalBalance(AccountStatus.ACTIVE, AccountStatus.BLOCKED, AccountStatus.CLOSED));

	}
}
