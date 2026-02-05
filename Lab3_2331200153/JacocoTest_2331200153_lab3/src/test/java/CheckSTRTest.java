import org.example.CheckSTR;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckSTRTest {

    @Test
    public void testConvertEmptyString() {
        CheckSTR str = new CheckSTR();
        assertEquals("", str.convert(""));
    }

    @Test
    public void testConvertOneChar() {
        CheckSTR str = new CheckSTR();
        assertEquals("1000010", str.convert("B"));
    }

    @Test
    public void testConvertPairOfChars() {
        CheckSTR str = new CheckSTR();
        assertEquals("10000111", str.convert("CD"));
    }

    @Test
    public void testConvertLowercase() {
        CheckSTR str = new CheckSTR();
        assertEquals("101101011", str.convert("xyz"));
    }
}
