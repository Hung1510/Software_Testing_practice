import org.example.CaesarShiftCipher;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarShiftCipherTest {
    @Test
    public void testCaesarShift_Shift3_Uppercase() {
        CaesarShiftCipher c = new CaesarShiftCipher();
        assertEquals("DEF", c.CaesarShift("ABC", 3));
    }
    @Test
    public void testCaesarShift_LongMessage() {
        CaesarShiftCipher c = new CaesarShiftCipher();
        assertEquals("KHOOR", c.CaesarShift("HELLO", 3));
    }
    @Test
    public void testCaesarShift_ShiftZero() {
        CaesarShiftCipher c = new CaesarShiftCipher();
        assertEquals("HELLO", c.CaesarShift("HELLO", 0));
    }
    @Test
    public void testCaesarShift_Shift26() {
        CaesarShiftCipher c = new CaesarShiftCipher();
        assertEquals("ABC", c.CaesarShift("ABC", 26));
    }
    @Test
    public void testCaesarShift_ShiftGreaterThan26() {
        CaesarShiftCipher c = new CaesarShiftCipher();
        assertEquals("DEF", c.CaesarShift("ABC", 29)); // 29 % 26 = 3
    }
    @Test
    public void testCaesarShift_SingleCharacter() {
        CaesarShiftCipher c = new CaesarShiftCipher();
        assertEquals("D", c.CaesarShift("A", 3));
    }
    @Test
    public void testCaesarShift_EmptyString() {
        CaesarShiftCipher c = new CaesarShiftCipher();
        assertEquals("", c.CaesarShift("", 3));
    }



}
