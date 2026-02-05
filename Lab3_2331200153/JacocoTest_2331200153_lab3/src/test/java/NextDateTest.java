import org.example.NextDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class NextDateTest {

    // Validation tests
    @Test
    public void testInvalidDayLow() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 5, 0);
        assertEquals("Value of day, not in the range 1...31", nd.next_date());
    }

    @Test
    public void testInvalidDayHigh() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 5, 32);
        assertEquals("Value of day, not in the range 1...31", nd.next_date());
    }

    @Test
    public void testInvalidMonthLow() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 0, 15);
        assertEquals("value of month, not in the range 1....12", nd.next_date());
    }

    @Test
    public void testInvalidMonthHigh() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 13, 15);
        assertEquals("value of month, not in the range 1....12", nd.next_date());
    }

    @Test
    public void testInvalid31InApril() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 4, 31);
        assertEquals("value of day, not in the range day<=30", nd.next_date());
    }

    @Test
    public void testInvalid31InSeptember() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 9, 31);
        assertEquals("value of day, not in the range day<=30", nd.next_date());
    }

    @Test
    public void testInvalid31InNovember() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 11, 31);
        assertEquals("value of day, not in the range day<=30", nd.next_date());
    }

    @Test
    public void testInvalidYearLow() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(1800, 5, 15);
        assertEquals("value of year, not in the range 1800.......2025", nd.next_date());
    }

    @Test
    public void testInvalidYearHigh() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2026, 5, 15);
        assertEquals("value of year, not in the range 1800.......2025", nd.next_date());
    }

    @Test
    public void testInvalidFeb30Leap() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 2, 30);
        assertEquals("invalid date input for leap year", nd.next_date());
    }

    @Test
    public void testInvalidFeb29NonLeap() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2019, 2, 29);
        assertEquals("invalid date input for not a leap year", nd.next_date());
    }

    // 31-day months
    @Test
    public void testJanuaryMid() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 1, 15);
        assertEquals("Next Date is :16-1-2020", nd.next_date());
    }

    @Test
    public void testMarchEnd() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 3, 31);
        assertEquals("Next Date is :1-4-2020", nd.next_date());
    }

    @Test
    public void testMayMid() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 5, 15);
        assertEquals("Next Date is :16-5-2020", nd.next_date());
    }

    // 30-day months
    @Test
    public void testAprilMid() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 4, 15);
        assertEquals("Next Date is :16-4-2020", nd.next_date());
    }

    @Test
    public void testJuneEnd() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 6, 30);
        assertEquals("Next Date is :1-7-2020", nd.next_date());
    }

    // December
    @Test
    public void testDecemberMid() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 12, 15);
        assertEquals("Next Date is :16-12-2020", nd.next_date());
    }

    @Test
    public void testDecemberEnd() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 12, 31);
        assertEquals("Next Date is :1-1-2021", nd.next_date());
    }

    @Test
    public void testDecember31Year2025Bug() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2025, 12, 31);
        assertEquals("Next Date is :1-1-2026", nd.next_date());
    }

    // February
    @Test
    public void testFeb27NonLeap() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2019, 2, 27);
        assertEquals("Next Date is :28-2-2019", nd.next_date());
    }

    @Test
    public void testFeb28NonLeap() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2019, 2, 28);
        assertEquals("Next Date is :1-3-2019", nd.next_date());
    }

    @Test
    public void testFeb28Leap() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 2, 28);
        assertEquals("Next Date is :29-2-2020", nd.next_date());
    }

    @Test
    public void testFeb29Leap() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2020, 2, 29);
        assertEquals("Next Date is :1-3-2020", nd.next_date());
    }

    @Test
    public void testLeapYearDivisibleBy400() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2000, 2, 29);
        assertEquals("Next Date is :1-3-2000", nd.next_date());
    }

    @Test
    public void testNonLeapYearDivisibleBy100() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(1900, 2, 28);
        assertEquals("Next Date is :1-3-1900", nd.next_date());
    }
}