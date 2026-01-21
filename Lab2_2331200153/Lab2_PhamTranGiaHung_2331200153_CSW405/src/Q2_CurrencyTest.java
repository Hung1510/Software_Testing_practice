import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Q2_CurrencyTest {
	Q2_Currency SEK, DKK, NOK, EUR;

	@Before
	public void setUp() throws Exception {
		// Setup currencies with exchange rates
		SEK = new Q2_Currency("SEK", 0.15);
		DKK = new Q2_Currency("DKK", 0.20);
		EUR = new Q2_Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}

	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate());
		assertEquals(Double.valueOf(1.5), EUR.getRate());
	}

	@Test
	public void testSetRate() {
		// Test new rate SEK
		SEK.setRate(0.18);
		assertEquals(Double.valueOf(0.18), SEK.getRate());

		// Test new rate EUR
		EUR.setRate(1.8);
		assertEquals(Double.valueOf(1.8), EUR.getRate());

		// Reset
		SEK.setRate(0.15);
		EUR.setRate(1.5);
	}

	@Test
	public void testGlobalValue() {
		// SEK rate = 0.15
		assertEquals(Integer.valueOf(15), SEK.universalValue(100));

		// DKK rate = 0.20
		assertEquals(Integer.valueOf(20), DKK.universalValue(100));

		// EUR rate = 1.5
		assertEquals(Integer.valueOf(150), EUR.universalValue(100));

		assertEquals(Integer.valueOf(30), SEK.universalValue(200));

		assertEquals(Integer.valueOf(3), SEK.universalValue(20));

		assertEquals(Integer.valueOf(0), SEK.universalValue(0));
	}

	@Test
	public void testValueInThisCurrency() {
		// 100 SEK = 15 universal (100 * 0.15)
		// 15 universal in DKK = 15 / 0.20 = 75
		assertEquals(Integer.valueOf(75), DKK.valueInThisCurrency(100, SEK));

		// 100 DKK = 20 universal (100 * 0.20)
		// 20 universal in SEK = 20 / 0.15 = 133
		assertEquals(Integer.valueOf(133), SEK.valueInThisCurrency(100, DKK));

		// 100 SEK = 15 universal
		// 15 universal in EUR = 15 / 1.5 = 10
		assertEquals(Integer.valueOf(10), EUR.valueInThisCurrency(100, SEK));

		// 100 EUR = 150 universal
		// 150 universal in SEK = 150 / 0.15 = 1000
		assertEquals(Integer.valueOf(1000), SEK.valueInThisCurrency(100, EUR));

		// Test same currency
		assertEquals(Integer.valueOf(100), SEK.valueInThisCurrency(100, SEK));

		// Test 0
		assertEquals(Integer.valueOf(0), DKK.valueInThisCurrency(0, SEK));
	}

}
