package banking;

import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts;
	private Long accountNumberCounter = 0L;
	
	public Bank() {
		this.accounts = new LinkedHashMap<Long, Account>();
	}

	private Account getAccount(Long accountNumber) {
		return accounts.get(accountNumber);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		Account acc = new CommercialAccount(company, accountNumberCounter++, pin, startingDeposit);
		accounts.put(accountNumberCounter, acc);
		return accountNumberCounter;
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		Account acc = new ConsumerAccount(person, accountNumberCounter++, pin, startingDeposit);
		accounts.put(accountNumberCounter, acc);
		return accountNumberCounter;
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		Account acc = accounts.get(accountNumber);
		return acc.validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
		return accounts.get(accountNumber).getBalance();
	}

	public void credit(Long accountNumber, double amount) {
		Account acc = accounts.get(accountNumber);
		acc.creditAccount(amount);
		accounts.put(accountNumber, acc);
	}

	public boolean debit(Long accountNumber, double amount) {
		Account acc = accounts.get(accountNumber);
		boolean result = acc.debitAccount(amount);
		accounts.put(accountNumber, acc);
		return result;
	}
}
