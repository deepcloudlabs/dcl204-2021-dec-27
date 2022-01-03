package com.example.banking.domain;

import com.example.banking.domain.exception.InsufficientBalanceException;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public interface TransferService {
	void transfer(String fromIdentity, String fromIban, String toIdentity, String toIban, double amount) 
			throws InsufficientBalanceException;
}
