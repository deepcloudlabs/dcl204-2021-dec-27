package com.example.banking.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public boolean transfer(String fromIdentity, String fromIban, String toIdentity, String toIban, double amount) {
		if (amount <= 0) return false;
		var fromCustomer = findCustomerByIdentity(fromIdentity);
		var toCustomer = findCustomerByIdentity(toIdentity);
		if (fromCustomer.isPresent() && toCustomer.isPresent()) {
			var fromAccount = fromCustomer.get().findAccount(fromIban);
			var toAccount = toCustomer.get().findAccount(toIban);
			if (fromAccount.isPresent() && fromAccount.get().getStatus().equals(AccountStatus.ACTIVE) && 
				toAccount.isPresent() && toAccount.get().getStatus().equals(AccountStatus.ACTIVE)){
				if(fromAccount.get().withdraw(amount)) {
					toAccount.get().deposit(amount);
					return true;
				}
			}
		}
		return false;
	}

}
