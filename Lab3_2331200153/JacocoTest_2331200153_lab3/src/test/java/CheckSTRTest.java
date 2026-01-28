import org.example.CheckSTR;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckSTRTest {
    CheckSTR checker = new CheckSTR();

    @Test
    public void testEmptyString() {
        String result = checker.convert("");
        assertEquals("", result);
    }
    @Test
    public void testSingleCharacter() {
        String result = checker.convert("A");
        assertEquals("1000001", result);
    }
    @Test
    public void testTwoCharacters() {
        String result = checker.convert("AB");
        assertEquals("10000011", result);
    }

    @Test
    public void testLowercaseString() {
        String result = checker.convert("abc");
        assertEquals("100100110", result);
    }

    @Test
    public void testWithNumber() {
        String result = checker.convert("A1");
        assertEquals("1110010", result);
    }
}
