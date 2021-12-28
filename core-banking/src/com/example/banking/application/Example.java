package com.example.banking.application;

import com.example.banking.domain.Account;
import com.example.banking.domain.Customer;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Example {

	public static void main(String[] args) {
		var jack = new Customer("11111111110", "jack bauer");
		jack.addAccount(new Account("tr1", 10_000));
		jack.addAccount(new Account("tr2", 20_000));
		jack.addAccount(new Account("tr3", 30_000));
		//jack.getAccounts().add(new Account("tr4",40_000));
		jack.getAccounts().clear();
		
	}

}
