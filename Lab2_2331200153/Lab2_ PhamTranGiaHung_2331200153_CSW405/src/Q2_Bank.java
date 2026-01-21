import java.util.Hashtable;

public class Q2_Bank {
	private Hashtable<String, Q2_Account> accountlist = new Hashtable<String, Q2_Account>();
	private String name;
	private Q2_Currency currency;
	
	/**
	 * New Bank
	 * @param name Name of this bank
	 * @param currency Base currency of this bank (If this is a Swedish bank, this might be a currency class representing SEK)
	 */
	Q2_Bank(String name, Q2_Currency currency) {
		this.name = name;
		this.currency = currency;
	}
	
	/**
	 * Get the name of this bank
	 * @return Name of this bank
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the Currency of this bank
	 * @return The Currency of this bank
	 */
	public Q2_Currency getCurrency() {
		return currency;
	}
	
	/**
	 * Open an account at this bank.
	 * @param accountid The ID of the account
	 * @throws Q2_AccountExistsException If the account already exists
	 */
	public void openAccount(String accountid) throws Q2_AccountExistsException {
		if (accountlist.containsKey(accountid)) {
			throw new Q2_AccountExistsException();
		}
		else {
			accountlist.get(accountid);
		}
	}
	
	/**
	 * Deposit money to an account
	 * @param accountid Account to deposit to
	 * @param money Money to deposit.
	 * @throws Q2_AccountDoesNotExistException If the account does not exist
	 */
	public void deposit(String accountid, Q2_Money money) throws Q2_AccountDoesNotExistException {
		if (accountlist.containsKey(accountid)) {
			throw new Q2_AccountDoesNotExistException();
		}
		else {
			Q2_Account account = accountlist.get(accountid);
			account.deposit(money);
		}
	}
	
	/**
	 * Withdraw money from an account
	 * @param accountid Account to withdraw from
	 * @param money Money to withdraw
	 * @throws Q2_AccountDoesNotExistException If the account does not exist
	 */
	public void withdraw(String accountid, Q2_Money money) throws Q2_AccountDoesNotExistException {
		if (!accountlist.containsKey(accountid)) {
			throw new Q2_AccountDoesNotExistException();
		}
		else {
			Q2_Account account = accountlist.get(accountid);
			account.deposit(money);
		}
	}
	
	/**
	 * Get the balance of an account
	 * @param accountid Account to get balance from
	 * @return Balance of the account
	 * @throws Q2_AccountDoesNotExistException If the account does not exist
	 */
	public Integer getBalance(String accountid) throws Q2_AccountDoesNotExistException {
		if (!accountlist.containsKey(accountid)) {
			throw new Q2_AccountDoesNotExistException();
		}
		else {
			return accountlist.get(accountid).getBalance().getAmount();
		}
	}

	/**
	 * Transfer money between two accounts
	 * @param fromaccount Id of account to deduct from in this Bank
	 * @param tobank Bank where receiving account resides
	 * @param toaccount Id of receiving account
	 * @param amount Amount of Money to transfer
	 * @throws Q2_AccountDoesNotExistException If one of the accounts do not exist
	 */
	public void transfer(String fromaccount, Q2_Bank tobank, String toaccount, Q2_Money amount) throws Q2_AccountDoesNotExistException {
		if (!accountlist.containsKey(fromaccount) || !tobank.accountlist.containsKey(toaccount)) {
			throw new Q2_AccountDoesNotExistException();
		}
		else {
			accountlist.get(fromaccount).withdraw(amount);
			tobank.accountlist.get(toaccount).deposit(amount);
		}		
	}

	/**
	 * Transfer money between two accounts on the same bank
	 * @param fromaccount Id of account to deduct from
	 * @param toaccount Id of receiving account
	 * @param amount Amount of Money to transfer
	 * @throws Q2_AccountDoesNotExistException If one of the accounts do not exist
	 */
	public void transfer(String fromaccount, String toaccount, Q2_Money amount) throws Q2_AccountDoesNotExistException {
		transfer(fromaccount, this, fromaccount, amount);
	}

	/**
	 * Add a timed payment
	 * @param accountid Id of account to deduct from in this Bank
	 * @param payid Id of timed payment
	 * @param interval Number of ticks between payments
	 * @param next Number of ticks till first payment
	 * @param amount Amount of Money to transfer each payment
	 * @param tobank Bank where receiving account resides
	 * @param toaccount Id of receiving account
	 */
	public void addTimedPayment(String accountid, String payid, Integer interval, Integer next, Q2_Money amount, Q2_Bank tobank, String toaccount) {
		Q2_Account account = accountlist.get(accountid);
		account.addTimedPayment(payid, interval, next, amount, tobank, toaccount);
	}
	
	/**
	 * Remove a timed payment
	 * @param accountid Id of account to remove timed payment from
	 * @param id Id of timed payment
	 */
	public void removeTimedPayment(String accountid, String id) {
		Q2_Account account = accountlist.get(accountid);
		account.removeTimedPayment(id);
	}
	
	/**
	 * A time unit passes in the system
	 */
	public void tick() throws Q2_AccountDoesNotExistException {
		for (Q2_Account account : accountlist.values()) {
			account.tick();
		}
	}	
}
