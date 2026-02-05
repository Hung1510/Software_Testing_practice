import org.example.CaesarShiftCipher;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarShiftCipherTest {

    @Test
    public void testAssignmentExample() {
        CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("EXXEGOEXSRGI", cipher.CaesarShift("ATTACKATONCE", 4));
    }

    @Test
    public void testBasicShift() {
        CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("XYZ", cipher.CaesarShift("UVW", 3));
    }

    @Test
    public void testEmptyInput() {
        CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("", cipher.CaesarShift("", 5));
    }

    @Test
    public void testLargeShiftValue() {
        CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("BCD", cipher.CaesarShift("XYZ", 30));
    }

    @Test
    public void testInvalidLowercase() {
        CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("invalid", cipher.CaesarShift("Hello", 3));
    }

    @Test
    public void testInvalidNumber() {
        CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("invalid", cipher.CaesarShift("ABC123", 3));
    }

    @Test
    public void testNegativeShift() {
        CaesarShiftCipher cipher = new CaesarShiftCipher();
        assertEquals("XYZ", cipher.CaesarShift("ABC", -3));
    }
}
