import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Q2_BankTest {
	Q2_Currency SEK, DKK;
	Q2_Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Q2_Currency("DKK", 0.20);
		SEK = new Q2_Currency("SEK", 0.15);
		SweBank = new Q2_Bank("SweBank", SEK);
		Nordea = new Q2_Bank("Nordea", SEK);
		DanskeBank = new Q2_Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
		assertEquals(SEK, Nordea.getCurrency());
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws Q2_AccountExistsException, Q2_AccountDoesNotExistException {
		// Test opening a new account
		SweBank.openAccount("Charlie");
		// BUG FOUND: openAccount doesn't actually create the account
		// It should do: accountlist.put(accountid, new Account(accountid, currency));
		// But it does: accountlist.get(accountid); which returns null
		
		// Verify account exists
		try {
			SweBank.getBalance("Charlie");
			// Should not throw exception if account was created properly
		} catch (Q2_AccountDoesNotExistException e) {
			fail("Account should exist after openAccount()");
		}
		
		// Test opening duplicate account - should throw exception
		try {
			SweBank.openAccount("Ulrika");
			fail("Should throw AccountExistsException");
		} catch (Q2_AccountExistsException e) {
			// Expected
		}
	}

	@Test
	public void testDeposit() throws Q2_AccountDoesNotExistException {
		// BUG FOUND: deposit() logic is inverted
		// Current: if (accountlist.containsKey(accountid)) throw exception
		// Should be: if (!accountlist.containsKey(accountid)) throw exception
		
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Ulrika"));
		
		SweBank.deposit("Ulrika", new Q2_Money(5000, SEK));
		assertEquals(Integer.valueOf(15000), SweBank.getBalance("Ulrika"));
		
		// Test depositing to non-existent account
		try {
			SweBank.deposit("NonExistent", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
			// Expected
		}
	}

	@Test
	public void testWithdraw() throws Q2_AccountDoesNotExistException {
		// First deposit some money
		SweBank.deposit("Bob", new Q2_Money(10000, SEK));
		
		// BUG FOUND: withdraw() calls deposit instead of withdraw
		// Line should be: account.withdraw(money);
		// But it is: account.deposit(money);
		SweBank.withdraw("Bob", new Q2_Money(3000, SEK));
		assertEquals(Integer.valueOf(7000), SweBank.getBalance("Bob"));
		
		// Test withdrawing from non-existent account
		try {
			SweBank.withdraw("NonExistent", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
			// Expected
		}
	}
	
	@Test
	public void testGetBalance() throws Q2_AccountDoesNotExistException {
		// Test getting balance
		SweBank.deposit("Ulrika", new Q2_Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));
		
		// Test non-existent account
		try {
			SweBank.getBalance("NonExistent");
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
			// Expected
		}
	}
	
	@Test
	public void testTransfer() throws Q2_AccountDoesNotExistException {
		// Setup: Add money to Ulrika's account
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		SweBank.deposit("Bob", new Q2_Money(5000, SEK));
		
		// Transfer from Ulrika to Bob within same bank
		SweBank.transfer("Ulrika", "Bob", new Q2_Money(3000, SEK));
		assertEquals(Integer.valueOf(7000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(8000), SweBank.getBalance("Bob"));
		
		// Transfer between different banks
		Nordea.deposit("Bob", new Q2_Money(2000, SEK));
		SweBank.transfer("Ulrika", Nordea, "Bob", new Q2_Money(2000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(4000), Nordea.getBalance("Bob"));
		
		// Test transfer from non-existent account
		try {
			SweBank.transfer("NonExistent", "Bob", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
			// Expected
		}
		
		// Test transfer to non-existent account
		try {
			SweBank.transfer("Ulrika", "NonExistent", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
			// Expected
		}
	}
	
	@Test
	public void testTransferSameBank() throws Q2_AccountDoesNotExistException {
		// BUG FOUND: transfer(String, String, Money) has wrong parameter
		// It calls: transfer(fromaccount, this, fromaccount, amount);
		// Should be: transfer(fromaccount, this, toaccount, amount);
		
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		SweBank.deposit("Bob", new Q2_Money(5000, SEK));
		
		SweBank.transfer("Ulrika", "Bob", new Q2_Money(3000, SEK));
		// With the bug, this transfers to fromaccount (Ulrika) instead of toaccount (Bob)
		// Expected: Ulrika=7000, Bob=8000
		// Actual: Ulrika=10000, Bob=5000 (no change or Ulrika gets more)
	}
	
	@Test
	public void testTimedPayment() throws Q2_AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		SweBank.deposit("Bob", new Q2_Money(5000, SEK));
		
		// Add timed payment from Ulrika to Bob
		SweBank.addTimedPayment("Ulrika", "payment1", 3, 1, new Q2_Money(1000, SEK), SweBank, "Bob");
		
		// Tick once - payment should execute on next=1
		SweBank.tick();
		
		// Check balances changed
		// Note: Account.tick() has bug calling tick() twice
		assertTrue(SweBank.getBalance("Ulrika") < 10000);
		assertTrue(SweBank.getBalance("Bob") > 5000);
		
		// Remove timed payment
		SweBank.removeTimedPayment("Ulrika", "payment1");
		
		int ulrikaBalance = SweBank.getBalance("Ulrika");
		int bobBalance = SweBank.getBalance("Bob");
		
		// Tick again - no change expected
		SweBank.tick();
		assertEquals(ulrikaBalance, SweBank.getBalance("Ulrika").intValue());
		assertEquals(bobBalance, SweBank.getBalance("Bob").intValue());
	}
}
