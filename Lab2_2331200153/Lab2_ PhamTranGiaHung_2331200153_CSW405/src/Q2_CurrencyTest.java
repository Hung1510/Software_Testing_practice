import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Q2_CurrencyTest {
	Q2_Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Q2_Currency("SEK", 0.15);
		DKK = new Q2_Currency("DKK", 0.20);
		EUR = new Q2_Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		fail("Write test case here");
	}
	
	@Test
	public void testGetRate() {
		fail("Write test case here");
	}
	
	@Test
	public void testSetRate() {
		fail("Write test case here");
	}
	
	@Test
	public void testGlobalValue() {
		fail("Write test case here");
	}
	
	@Test
	public void testValueInThisCurrency() {
		fail("Write test case here");
	}

}
