package com.example.banking.domain;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public interface TransferService {
	boolean transfer(String fromIdentity, String fromIban, String toIdentity, String toIban, double amount);
}
