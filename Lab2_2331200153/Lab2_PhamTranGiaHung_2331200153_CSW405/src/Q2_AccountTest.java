import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Q2_AccountTest {
	Q2_Currency SEK, DKK;
	Q2_Bank Nordea;
	Q2_Bank DanskeBank;
	Q2_Bank SweBank;
	Q2_Account testAccount;

	@Before
	public void setUp() throws Exception {
		SEK = new Q2_Currency("SEK", 0.15);
		SweBank = new Q2_Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Q2_Account("Hans", SEK);
		testAccount.deposit(new Q2_Money(10000000, SEK));

		SweBank.deposit("Alice", new Q2_Money(1000000, SEK));
	}

	@Test
	public void testAddRemoveTimedPayment() {
		// Test adding a timed payment
		assertFalse(testAccount.timedPaymentExists("payment1"));

		testAccount.addTimedPayment("payment1", 5, 2, new Q2_Money(1000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));

		// Test removing a timed payment
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));

		// Test adding multiple payments
		testAccount.addTimedPayment("payment1", 5, 2, new Q2_Money(1000, SEK), SweBank, "Alice");
		testAccount.addTimedPayment("payment2", 3, 1, new Q2_Money(500, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("payment1"));
		assertTrue(testAccount.timedPaymentExists("payment2"));

		// Remove one
		testAccount.removeTimedPayment("payment1");
		assertFalse(testAccount.timedPaymentExists("payment1"));
		assertTrue(testAccount.timedPaymentExists("payment2"));
	}

	@Test
	public void testTimedPayment() throws Q2_AccountDoesNotExistException {
		// Add a timed payment that executes after 2 ticks
		int initialBalance = testAccount.getBalance().getAmount();
		testAccount.addTimedPayment("payment1", 3, 2, new Q2_Money(1000, SEK), SweBank, "Alice");

		// First tick no payment next becomes 1
		testAccount.tick();
		assertEquals("Balance should not change on first tick", initialBalance, testAccount.getBalance().getAmount().intValue());

		// Second tick no payment next becomes 0, payment execute after 0)
		testAccount.tick();
		assertEquals("Balance should not change on second tick", initialBalance, testAccount.getBalance().getAmount().intValue());

		// Third tick payment executes
		testAccount.tick();
		assertEquals("Balance should decrease by 1000 after third tick", initialBalance - 1000, testAccount.getBalance().getAmount().intValue());

		// Fourth tick interval is 3, payment execute again after 3 ticks
		testAccount.tick();
		assertEquals("Balance should not change on fourth tick", initialBalance - 1000, testAccount.getBalance().getAmount().intValue());
	}

	@Test
	public void testAddWithdraw() {
		int initial = testAccount.getBalance().getAmount();

		testAccount.deposit(new Q2_Money(5000, SEK));
		assertEquals(initial + 5000, testAccount.getBalance().getAmount().intValue());

		testAccount.withdraw(new Q2_Money(2000, SEK));
		assertEquals(initial + 5000 - 2000, testAccount.getBalance().getAmount().intValue());

		testAccount.deposit(new Q2_Money(10000, SEK));
		testAccount.withdraw(new Q2_Money(3000, SEK));
		assertEquals(initial + 5000 - 2000 + 10000 - 3000, testAccount.getBalance().getAmount().intValue());
	}

	@Test
	public void testGetBalance() {
		assertEquals(Integer.valueOf(10000000), testAccount.getBalance().getAmount());

		testAccount.deposit(new Q2_Money(1000, SEK));
		assertEquals(Integer.valueOf(10001000), testAccount.getBalance().getAmount());

		testAccount.withdraw(new Q2_Money(500, SEK));
		assertEquals(Integer.valueOf(10000500), testAccount.getBalance().getAmount());
	}
}
