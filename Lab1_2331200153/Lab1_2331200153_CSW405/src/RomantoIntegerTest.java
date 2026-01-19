import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RomantoIntegerTest {

    @Test
    public void testRomanToInt() {
        RomantoInteger rti = new RomantoInteger();
        assertEquals(3, rti.convert("III"));
        assertEquals(4, rti.convert("IV"));
        assertEquals(9, rti.convert("IX"));
        assertEquals(23, rti.convert("XXIII"));
        assertEquals(1994, rti.convert("MCMXCIV"));
    }
}
