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
        // 3 so Male
        Q1_PersonalNumber pn1 = new Q1_PersonalNumber("6408233234");
        assertEquals("Male", pn1.getSex());

        // 8 so Female
        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8505152382");
        assertEquals("Female", pn2.getSex());

        // 1 so Male
        Q1_PersonalNumber pn3 = new Q1_PersonalNumber("9203121018");
        assertEquals("Male", pn3.getSex());
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
        //  32 invalid
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
}
