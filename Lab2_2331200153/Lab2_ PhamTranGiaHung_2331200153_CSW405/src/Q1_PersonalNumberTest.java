import org.junit.Test;
import static org.junit.Assert.*;

public class Q1_PersonalNumberTest {

    @Test
    public void testChecksumCalculation() {
        // Test case from assignment example: 640823-3234
        Q1_PersonalNumber pn1 = new Q1_PersonalNumber("6408233234");
        assertEquals(4, pn1.getCheckSum());

        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8505152382");
        assertEquals(2, pn2.getCheckSum());
    }

    @Test
    public void testGetDate() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("640823", pn.getDate());

        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8505152382");
        assertEquals("850515", pn2.getDate());
    }

    @Test
    public void testGetYear() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("64", pn.getYear());

        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("0001010172");
        assertEquals("00", pn2.getYear());
    }

    @Test
    public void testGetMonth() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("08", pn.getMonth());

        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8512152383");
        assertEquals("12", pn2.getMonth());
    }

    @Test
    public void testGetSex() {
        // Z = 3 (odd) -> Male
        Q1_PersonalNumber pn1 = new Q1_PersonalNumber("6408233234");
        assertEquals("Male", pn1.getSex());

        // Z = 8 (even) -> Female
        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8505152382");
        assertEquals("Female", pn2.getSex());

        // Z = 1 (odd) -> Male
        Q1_PersonalNumber pn3 = new Q1_PersonalNumber("9203121018");
        assertEquals("Male", pn3.getSex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPersonalNumberLength() {
        // Too short
        new Q1_PersonalNumber("123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPersonalNumberFormat() {
        // Contains non-digit characters
        new Q1_PersonalNumber("64082A3234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidChecksum() {
        new Q1_PersonalNumber("6408233235");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonth() {
        new Q1_PersonalNumber("6413233234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDay() {
        // Day 32 is invalid
        new Q1_PersonalNumber("6408323234");
    }

    @Test
    public void testGetDay() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("23", pn.getDay());
    }

    @Test
    public void testGetSerialNumber() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("323", pn.getSerialNumber());
    }

    @Test
    public void testEdgeCaseChecksumZero() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("5001010080");
        assertEquals(0, pn.getCheckSum());
    }

    // more for funni
    @Test
    public void testMultipleValidNumbers() {
        // CORRECTED: checksum should be 9, not 6
        Q1_PersonalNumber pn1 = new Q1_PersonalNumber("9001011239");
        assertEquals("900101", pn1.getDate());
        assertEquals(9, pn1.getCheckSum());

        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("7506231237");
        assertEquals("750623", pn2.getDate());
        assertEquals(7, pn2.getCheckSum());
    }
}
