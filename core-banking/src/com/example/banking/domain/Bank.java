package com.example.banking.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.banking.domain.exception.InsufficientBalanceException;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class Bank implements TransferService {
	private final int id;
	private String commercialName;
	// TODO: Create an enum type: BankType
	// NOTE: BankType has two values: PUBLIC and PRIVATE
	private final BankType type;
	private Map<String, Customer> customers = new HashMap<>();

	public Bank(int id, String commercialName, BankType type) {
		this.id = id;
		this.commercialName = commercialName;
		this.type = type;
	}

	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	public int getId() {
		return id;
	}

	public BankType getType() {
		return type;
	}

	public List<Customer> getCustomers() {
		return customers.values().stream().toList();
	}
	
	public Customer createCustomer(String identity, String fullname) {
		var customer = new Customer(identity, fullname);
		customers.put(identity, customer);
		return customer;
	}

	public Optional<Customer> findCustomerByIdentity(String identity) {
		return Optional.ofNullable(customers.get(identity));
	}

	public double getTotalBalance(AccountStatus... status) {
		return customers.values()
		         .stream()
		         .map(Customer::getAccounts)
		         .flatMap(Collection::stream)
		         .filter(account -> Arrays.stream(status).filter(st -> st.equals(account.getStatus())).findFirst().isPresent())
		         .mapToDouble(Account::getBalance)
		         .sum();
	}

	@Override
	public void transfer(String fromIdentity, String fromIban, String toIdentity, String toIban, double amount) throws InsufficientBalanceException {
		if (amount <= 0) 
			throw new IllegalArgumentException("Amount must be positive.");
		var fromCustomer = findCustomerByIdentity(fromIdentity);
		if (fromCustomer.isEmpty())
			throw new IllegalArgumentException(String.format("Cannot find the customer %s",fromIdentity));
		var toCustomer = findCustomerByIdentity(toIdentity);
		if (toCustomer.isEmpty())
			throw new IllegalArgumentException(String.format("Cannot find the customer %s",toIdentity));
		var fromAccount = fromCustomer.get().findAccount(fromIban);
		if (fromAccount.isEmpty())
			throw new IllegalArgumentException(String.format("Cannot find the account %s",fromIban));
		var toAccount = toCustomer.get().findAccount(toIban);
		if (toAccount.isEmpty())
			throw new IllegalArgumentException(String.format("Cannot find the account %s",toIban));
		if (!fromAccount.get().getStatus().equals(AccountStatus.ACTIVE))
			throw new IllegalArgumentException(String.format("The account %s is not active",fromIban));
		if (!toAccount.get().getStatus().equals(AccountStatus.ACTIVE))
			throw new IllegalArgumentException(String.format("The account %s is not active",toIban));
		try {
			fromAccount.get().withdraw(amount);
			toAccount.get().deposit(amount);			
		}catch (InsufficientBalanceException e) {
			System.err.println(e.getMessage());
			throw e; // re-throw
		}
	}

}
