package com.example.banking.application;

import com.example.banking.domain.Account;
import com.example.banking.domain.Customer;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Example {

	public static void main(String[] args) {
		var jack = new Customer("11111111110", "jack bauer");
		var kate = new Customer("11111111110", "kate austen");
		jack.addAccount(new Account("tr1", 10_000));
		jack.addAccount(new Account("tr2", 20_000));
		jack.addAccount(new Account("tr3", 30_000));
		kate.addAccount(new Account("tr4",20_000));
		System.out.println(jack);
		System.out.println(kate);
		var balance = jack.removeAccount("tr2")
		    .orElseThrow(() -> new IllegalArgumentException(
		    		"Cannot find the account to remove"))
		    .withdrawAll();
		System.out.println(balance);
		var account = jack.removeAccount("tr4");
		if (account.isPresent())
			account.get().withdrawAll();
		System.out.println(jack);
		
	}

}
