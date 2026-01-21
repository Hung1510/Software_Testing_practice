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
		SweBank.openAccount("Charlie");

		assertEquals(Integer.valueOf(0), SweBank.getBalance("Charlie"));

		try {
			SweBank.openAccount("Ulrika");
			fail("Should throw AccountExistsException");
		} catch (Q2_AccountExistsException e) {
		}
	}

	@Test
	public void testDeposit() throws Q2_AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Ulrika"));

		SweBank.deposit("Ulrika", new Q2_Money(5000, SEK));
		assertEquals(Integer.valueOf(15000), SweBank.getBalance("Ulrika"));

		// depositing to non existent account
		try {
			SweBank.deposit("NonExistent", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
		}
	}

	@Test
	public void testWithdraw() throws Q2_AccountDoesNotExistException {
		SweBank.deposit("Bob", new Q2_Money(10000, SEK));
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Bob"));

		SweBank.withdraw("Bob", new Q2_Money(3000, SEK));
		assertEquals(Integer.valueOf(7000), SweBank.getBalance("Bob"));

		// depositing to non existent account
		try {
			SweBank.withdraw("NonExistent", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
		}
	}

	@Test
	public void testGetBalance() throws Q2_AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Q2_Money(5000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));

		try {
			SweBank.getBalance("NonExistent");
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
		}
	}

	@Test
	public void testTransfer() throws Q2_AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		SweBank.deposit("Bob", new Q2_Money(5000, SEK));

		// Transfer Ulrika to Bob same bank
		SweBank.transfer("Ulrika", "Bob", new Q2_Money(3000, SEK));
		assertEquals(Integer.valueOf(7000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(8000), SweBank.getBalance("Bob"));

		// Transfer between different banks
		Nordea.deposit("Bob", new Q2_Money(2000, SEK));
		SweBank.transfer("Ulrika", Nordea, "Bob", new Q2_Money(2000, SEK));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(4000), Nordea.getBalance("Bob"));

		try {
			SweBank.transfer("NonExistent", "Bob", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
		}

		try {
			SweBank.transfer("Ulrika", "NonExistent", new Q2_Money(1000, SEK));
			fail("Should throw AccountDoesNotExistException");
		} catch (Q2_AccountDoesNotExistException e) {
		}
	}

	@Test
	public void testTransferSameBank() throws Q2_AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		SweBank.deposit("Bob", new Q2_Money(5000, SEK));

		SweBank.transfer("Ulrika", "Bob", new Q2_Money(3000, SEK));

		assertEquals(Integer.valueOf(7000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(8000), SweBank.getBalance("Bob"));
	}

	@Test
	public void testTimedPayment() throws Q2_AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Q2_Money(10000, SEK));
		SweBank.deposit("Bob", new Q2_Money(5000, SEK));

		// Add timed payment  executes after 2 tick
		SweBank.addTimedPayment("Ulrika", "payment1", 3, 2, new Q2_Money(1000, SEK), SweBank, "Bob");

		// Tick once no payment
		SweBank.tick();
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Bob"));

		// Tick twice no payment
		SweBank.tick();
		assertEquals(Integer.valueOf(10000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(5000), SweBank.getBalance("Bob"));

		// Tick three times payment execute
		SweBank.tick();
		assertEquals(Integer.valueOf(9000), SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(6000), SweBank.getBalance("Bob"));

		// Remove timed payment
		SweBank.removeTimedPayment("Ulrika", "payment1");

		int ulrikaBalance = SweBank.getBalance("Ulrika");
		int bobBalance = SweBank.getBalance("Bob");

		// Tick again no change since payment removed
		SweBank.tick();
		assertEquals(ulrikaBalance, SweBank.getBalance("Ulrika").intValue());
		assertEquals(bobBalance, SweBank.getBalance("Bob").intValue());
	}
}
