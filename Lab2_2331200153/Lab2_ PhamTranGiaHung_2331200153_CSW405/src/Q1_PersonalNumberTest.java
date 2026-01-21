import org.junit.Test;
import static org.junit.Assert.*;

public class Q1_PersonalNumberTest {
    
    @Test
    public void testChecksumCalculation() {
        // Test case from assignment example: 640823-3234
        Q1_PersonalNumber pn1 = new Q1_PersonalNumber("6408233234");
        assertEquals(4, pn1.getCheckSum());
        
        // Additional test cases
        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8505152386");
        assertEquals(6, pn2.getCheckSum());
    }
    
    @Test
    public void testGetDate() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("640823", pn.getDate());
        
        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8505152386");
        assertEquals("850515", pn2.getDate());
    }
    
    @Test
    public void testGetYear() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("64", pn.getYear());
        
        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("0001010123");
        assertEquals("00", pn2.getYear());
    }
    
    @Test
    public void testGetMonth() {
        Q1_PersonalNumber pn = new Q1_PersonalNumber("6408233234");
        assertEquals("08", pn.getMonth());
        
        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8512152386");
        assertEquals("12", pn2.getMonth());
    }
    
    @Test
    public void testGetSex() {
        // Z = 3 (odd) -> Male
        Q1_PersonalNumber pn1 = new Q1_PersonalNumber("6408233234");
        assertEquals("Male", pn1.getSex());
        
        // Z = 8 (even) -> Female
        Q1_PersonalNumber pn2 = new Q1_PersonalNumber("8505152386");
        assertEquals("Female", pn2.getSex());
        
        // Z = 1 (odd) -> Male
        Q1_PersonalNumber pn3 = new Q1_PersonalNumber("9203121015");
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
        // Correct format but wrong checksum (last digit should be 4, not 5)
        new Q1_PersonalNumber("6408233235");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonth() {
        // Month 13 is invalid
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
        // When the final result is 10, checksum should be 0
        Q1_PersonalNumber pn = new Q1_PersonalNumber("7101011230");
        assertEquals(0, pn.getCheckSum());
    }
}
