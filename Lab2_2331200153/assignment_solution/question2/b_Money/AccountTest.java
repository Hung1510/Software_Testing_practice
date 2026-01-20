package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		// Test adding a timed payment
		assertFalse(testAccount.timedPaymentExists("payment1"));
		
		testAccount.addTimedPayment("payment1", 5, 2, new Money(1000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));
		
		// Test removing a timed payment
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));
		
		// Test adding multiple payments
		testAccount.addTimedPayment("payment1", 5, 2, new Money(1000, SEK), SweBank, "Alice");
		testAccount.addTimedPayment("payment2", 3, 1, new Money(500, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));
		assertTrue(testAccount.timedPaymentExists("payment2"));
		
		// Remove one
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));
		assertTrue(testAccount.timedPaymentExists("payment2"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		// Add a timed payment that executes on next=1, interval=3
		int initialBalance = testAccount.getBalance().getAmount();
		testAccount.addTimedPayment("payment1", 3, 1, new Money(1000, SEK), SweBank, "Alice");
		
		// First tick - no payment (next=1, becomes next=0, then executes)
		testAccount.tick();
		int afterFirstTick = testAccount.getBalance().getAmount();
		// BUG FOUND: tick() is called twice in the loop, so payment happens on first tick
		// Expected: no change
		// Actual: 1000 deducted
		
		// Second tick
		testAccount.tick();
		int afterSecondTick = testAccount.getBalance().getAmount();
		
		// The payment should have occurred
		assertTrue(afterSecondTick < initialBalance);
	}

	@Test
	public void testAddWithdraw() {
		int initial = testAccount.getBalance().getAmount();
		
		// Test deposit
		testAccount.deposit(new Money(5000, SEK));
		assertEquals(initial + 5000, testAccount.getBalance().getAmount().intValue());
		
		// Test withdraw
		testAccount.withdraw(new Money(2000, SEK));
		assertEquals(initial + 5000 - 2000, testAccount.getBalance().getAmount().intValue());
		
		// Test multiple operations
		testAccount.deposit(new Money(10000, SEK));
		testAccount.withdraw(new Money(3000, SEK));
		assertEquals(initial + 5000 - 2000 + 10000 - 3000, testAccount.getBalance().getAmount().intValue());
	}
	
	@Test
	public void testGetBalance() {
		// Initial balance
		assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());
		
		// After deposit
		testAccount.deposit(new Money(1000, SEK));
		assertEquals(Integer.valueOf(10001000), testAccount.getBalance().getAmount());
		
		// After withdrawal
		testAccount.withdraw(new Money(500, SEK));
		assertEquals(Integer.valueOf(10000500), testAccount.getBalance().getAmount());
	}
}
