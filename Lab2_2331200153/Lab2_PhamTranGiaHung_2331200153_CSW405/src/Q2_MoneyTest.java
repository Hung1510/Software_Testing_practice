import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Q2_MoneyTest {
	Q2_Currency SEK, DKK, EUR;
	Q2_Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Q2_Currency("SEK", 0.15);
		DKK = new Q2_Currency("DKK", 0.20);
		EUR = new Q2_Currency("EUR", 1.5);
		SEK100 = new Q2_Money(10000, SEK);
		EUR10 = new Q2_Money(1000, EUR);
		SEK200 = new Q2_Money(20000, SEK);
		EUR20 = new Q2_Money(2000, EUR);
		SEK0 = new Q2_Money(0, SEK);
		EUR0 = new Q2_Money(0, EUR);
		SEKn100 = new Q2_Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(Integer.valueOf(10000), SEK100.getAmount());
		assertEquals(Integer.valueOf(1000), EUR10.getAmount());
		assertEquals(Integer.valueOf(0), SEK0.getAmount());
		assertEquals(Integer.valueOf(-10000), SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
		assertEquals("10.0 EUR", EUR10.toString());
		assertEquals("0.0 SEK", SEK0.toString());
	}

	@Test
	public void testUniversalValue() {
		// SEK100 = 10000 * 0.15 = 1500
		assertEquals(Integer.valueOf(1500), SEK100.universalValue());
		// EUR10 = 1000 * 1.5 = 1500
		assertEquals(Integer.valueOf(1500), EUR10.universalValue());
	}

	@Test
	public void testEquals() {
		// Same universal value
		assertTrue(SEK100.equals(EUR10));
		// Different universal values
		assertFalse(SEK100.equals(SEK200));
		// Zero amounts
		assertTrue(SEK0.equals(EUR0));
	}

	@Test
	public void testAdd() {
		Q2_Money result = SEK100.add(EUR10);
		// EUR10 = 1500 universal, convert to SEK: 1500/0.15 = 10000
		// SEK100 + EUR10 (as SEK) = 10000 + 10000 = 20000 SEK
		assertEquals(Integer.valueOf(20000), result.getAmount());
		assertEquals(SEK, result.getCurrency());
	}

	@Test
	public void testSub() {
		Q2_Money result = SEK200.sub(EUR10);
		// EUR10 = 1500 universal, convert to SEK: 1500/0.15 = 10000
		// SEK200 - EUR10 (as SEK) = 20000 - 10000 = 10000 SEK
		assertEquals(Integer.valueOf(10000), result.getAmount());
		assertEquals(SEK, result.getCurrency());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
		assertTrue(EUR0.isZero());
		assertFalse(SEK100.isZero());
		assertFalse(SEKn100.isZero());
	}

	@Test
	public void testNegate() {
		Q2_Money negated = SEK100.negate();
		assertEquals(Integer.valueOf(-10000), negated.getAmount());
		assertEquals(SEK, negated.getCurrency());
		
		Q2_Money doubleNegated = negated.negate();
		assertEquals(Integer.valueOf(10000), doubleNegated.getAmount());
	}

	@Test
	public void testCompareTo() {
		// Same value
		assertEquals(0, SEK100.compareTo(EUR10));
		// SEK100 < SEK200
		assertTrue(SEK100.compareTo(SEK200) < 0);
		// SEK200 > SEK100
		assertTrue(SEK200.compareTo(SEK100) > 0);
		// Negative vs positive
		assertTrue(SEKn100.compareTo(SEK100) < 0);
	}
}
