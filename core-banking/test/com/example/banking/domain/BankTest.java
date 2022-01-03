package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.banking.domain.exception.InsufficientBalanceException;

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
		assertEquals(210_000,
				garantiBbva.getTotalBalance(AccountStatus.ACTIVE, AccountStatus.BLOCKED, AccountStatus.CLOSED));
	}

	@Test
	public void transferMoneyBetweenTwoCustomerAccountsSuccessfuly() throws Throwable {
		var garantiBbva = new Bank(1, "Garanti BBVA", BankType.PRIVATE);
		var jack = garantiBbva.createCustomer("57487680140", "jack bauer");
		var kate = garantiBbva.createCustomer("80450397882", "kate austen");
		Account tr1 = new Account("tr1", 10_000, AccountStatus.ACTIVE);
		Account tr4 = new Account("tr4", 40_000, AccountStatus.ACTIVE);
		jack.addAccount(tr1);
		jack.addAccount(new CheckingAccount("tr2", 20_000, AccountStatus.BLOCKED, 5_000));
		jack.addAccount(new Account("tr3", 30_000, AccountStatus.CLOSED));
		kate.addAccount(tr4);
		kate.addAccount(new Account("tr5", 50_000, AccountStatus.BLOCKED));
		kate.addAccount(new CheckingAccount("tr6", 60_000, AccountStatus.CLOSED, 3_000));
		garantiBbva.transfer("57487680140", "tr1", "80450397882", "tr4", 500);
		assertEquals(9_500, tr1.getBalance());
		assertEquals(40_500, tr4.getBalance());
		assertEquals(210_000,
				garantiBbva.getTotalBalance(AccountStatus.ACTIVE, AccountStatus.BLOCKED, AccountStatus.CLOSED));
	}

	@Test
	public void transferMoneyBetweenTwoCustomerAccountsFails() throws Throwable {
		var garantiBbva = new Bank(1, "Garanti BBVA", BankType.PRIVATE);
		var jack = garantiBbva.createCustomer("57487680140", "jack bauer");
		var kate = garantiBbva.createCustomer("80450397882", "kate austen");
		Account tr1 = new Account("tr1", 10_000, AccountStatus.ACTIVE);
		Account tr4 = new Account("tr4", 40_000, AccountStatus.ACTIVE);
		jack.addAccount(tr1);
		jack.addAccount(new CheckingAccount("tr2", 20_000, AccountStatus.BLOCKED, 5_000));
		jack.addAccount(new Account("tr3", 30_000, AccountStatus.CLOSED));
		kate.addAccount(tr4);
		kate.addAccount(new Account("tr5", 50_000, AccountStatus.BLOCKED));
		kate.addAccount(new CheckingAccount("tr6", 60_000, AccountStatus.CLOSED, 3_000));
		Assertions.assertAll(
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("1111111110", "tr1", "80450397882", "tr4", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr1", "1111111110", "tr4", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr2", "80450397882", "tr4", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr3", "80450397882", "tr4", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr2", "80450397882", "tr5", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr2", "80450397882", "tr6", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr7", "80450397882", "tr6", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr2", "80450397882", "tr8", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr2", "80450397882", "tr6", 500)),
				() -> assertThrows(IllegalArgumentException.class,() -> garantiBbva.transfer("57487680140", "tr1", "80450397882", "tr6", 500)),
				() -> assertThrows(InsufficientBalanceException.class,() -> garantiBbva.transfer("57487680140", "tr1", "80450397882", "tr4", 10_001))
		);
		assertEquals(10_000, tr1.getBalance());
		assertEquals(40_000, tr4.getBalance());
		assertEquals(210_000,garantiBbva.getTotalBalance(AccountStatus.ACTIVE, AccountStatus.BLOCKED, AccountStatus.CLOSED));
	}

	@Test
	public void transferNegativeAmountBetweenTwoCustomerAccountsFails() throws Throwable {
		var garantiBbva = new Bank(1, "Garanti BBVA", BankType.PRIVATE);
		var jack = garantiBbva.createCustomer("57487680140", "jack bauer");
		var kate = garantiBbva.createCustomer("80450397882", "kate austen");
		Account tr1 = new Account("tr1", 10_000, AccountStatus.ACTIVE);
		Account tr4 = new Account("tr4", 40_000, AccountStatus.ACTIVE);
		jack.addAccount(tr1);
		jack.addAccount(new CheckingAccount("tr2", 20_000, AccountStatus.BLOCKED, 5_000));
		jack.addAccount(new Account("tr3", 30_000, AccountStatus.CLOSED));
		kate.addAccount(tr4);
		kate.addAccount(new Account("tr5", 50_000, AccountStatus.BLOCKED));
		kate.addAccount(new CheckingAccount("tr6", 60_000, AccountStatus.CLOSED, 3_000));
		assertThrows(IllegalArgumentException.class,
				() -> garantiBbva.transfer("57487680140", "tr1", "80450397882", "tr4", -10));
		assertEquals(10_000, tr1.getBalance());
		assertEquals(40_000, tr4.getBalance());
		assertEquals(210_000,
				garantiBbva.getTotalBalance(AccountStatus.ACTIVE, AccountStatus.BLOCKED, AccountStatus.CLOSED));
	}

}
