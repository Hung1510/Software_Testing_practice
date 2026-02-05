package Test;

import org.junit.Test;
import static org.junit.Assert.*;
import Class.EncryptMessage;

public class EncryptMessageTest {
    @Test
    public void testEncryptBasic() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("yzwxv", encr.Encryption("abcde"));
    }

    @Test
    public void testOddLength() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("yzwxv", encr.Encryption("abcde"));
    }

    @Test
    public void testSingleCharacter() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("z", encr.Encryption("a"));
    }

    @Test
    public void testEncryption_EmptyString() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("", encr.Encryption(""));
    }

    @Test
    public void testSwapOnlyEffect() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("yz", encr.Encryption("ab"));
    }

    @Test
    public void testEndOfAlphabet() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("ba", encr.Encryption("zy"));
    }

    @Test
    public void testRepeatedCharacters() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("zzzz", encr.Encryption("aaaa"));
    }

    @Test
    public void testLongString() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("yzwxuvst", encr.Encryption("abcdefgh"));
    }

    @Test
    public void testInvalidCharacter_Uppercase() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("invalid", encr.Encryption("abcdefghijkA"));
    }

    @Test
    public void testInvalidCharacter_Number() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("invalid", encr.Encryption("abc123"));
    }

    @Test
    public void testInvalidCharacter_SpecialChar() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("invalid", encr.Encryption("ab@cd"));
    }

    @Test
    public void testInvalidCharacter_Space() {
        EncryptMessage encr = new EncryptMessage();
        assertEquals("invalid", encr.Encryption("ab cd"));
    }
}
