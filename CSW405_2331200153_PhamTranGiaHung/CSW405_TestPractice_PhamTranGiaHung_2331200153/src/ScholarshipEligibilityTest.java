import org.junit.Test;
import static org.junit.Assert.*;

public class ScholarshipEligibilityTest {
    //gpa below min
    @Test
    public void testGpaBelowMin() {
        assertEquals("Invalid", ScholarshipEligibility.calculateScholarshipEligibility(-0.1, 5, 150));
    }

    //gpa at min
    @Test
    public void testGpaAtMin() {
        assertEquals("Not eligible", ScholarshipEligibility.calculateScholarshipEligibility(0.0, 5, 150));
    }

    //gpa slightly above min
    @Test
    public void testGpaMinPlus() {
        assertEquals("Not eligible", ScholarshipEligibility.calculateScholarshipEligibility(0.1, 5, 150));
    }

    //gpa nominal
    @Test
    public void testGpaNominal() {
        assertEquals("Not eligible", ScholarshipEligibility.calculateScholarshipEligibility(2.0, 5, 150));
    }

    //gpa below max
    @Test
    public void testGpaMaxMinus() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(3.9, 5, 150));
    }

    //gpa max
    @Test
    public void testGpaAtMax() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(4.0, 5, 150));
    }

    //gpa above max
    @Test
    public void testGpaAboveMax() {
        assertEquals("Invalid", ScholarshipEligibility.calculateScholarshipEligibility(4.1, 5, 150));
    }

    //activities test
    //activities below min
    @Test
    public void testExtActBelowMin() {
        assertEquals("Invalid", ScholarshipEligibility.calculateScholarshipEligibility(3.5, -1, 150));
    }

    //activities at min
    @Test
    public void testExtActAtMin() {
        assertEquals("Not eligible", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 0, 150));
    }

    //activities at above min
    @Test
    public void testExtActMinPlus1() {
        assertEquals("Not eligible", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 1, 150));
    }

    //activities at nom
    @Test
    public void testExtActNominal() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, 150));
    }

    //activities at below max
    @Test
    public void testExtActMaxMinus1() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 9, 150));
    }

    //activities at max
    @Test
    public void testExtActAtMax() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 10, 150));
    }

    //activities above max
    @Test
    public void testExtActAboveMax() {
        assertEquals("Invalid", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 11, 150));
    }

    //volunteer below min
    @Test
    public void testVolHoursBelowMin() {
        assertEquals("Invalid", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, -1));
    }

    //volunteer at min
    @Test
    public void testVolHoursAtMin() {
        assertEquals("Not eligible", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, 0));
    }

    //volunteer above min
    @Test
    public void testVolHoursMinPlus1() {
        assertEquals("Not eligible", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, 1));
    }

    //volunteer at nom
    @Test
    public void testVolHoursNominal() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, 150));
    }

    //volunteer  below max
    @Test
    public void testVolHoursMaxMinus1() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, 299));
    }

    //volunteer at max
    @Test
    public void testVolHoursAtMax() {
        assertEquals("Full scholarship", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, 300));
    }

    // volunteer above max
    @Test
    public void testVolHoursAboveMax() {
        assertEquals("Invalid", ScholarshipEligibility.calculateScholarshipEligibility(3.5, 5, 301));
    }
}