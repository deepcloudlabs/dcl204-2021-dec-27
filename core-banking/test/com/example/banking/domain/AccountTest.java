package com.example.banking.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
class AccountTest {

	@Test
	@DisplayName("create account object successfuly")
	void createObjectSuccessfuly() {
		// 1. Test Fixture/Setup
		// 2. Call exercise method
		var acc = new Account("tr1", 10_000);
		// 3. Verification
		assertEquals("tr1", acc.getIban());
		assertEquals(10_000, acc.getBalance());
		// 4. Tear-down
	}

}
