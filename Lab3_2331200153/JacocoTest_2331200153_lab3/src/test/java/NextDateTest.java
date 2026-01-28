import org.example.NextDate;
import org.junit.Test;
import static org.junit.Assert.*;

public class NextDateTest {

    @Test
    public void testNextDate_EndOfYear() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2025, 12, 31);
        assertEquals("Next Date is :1-1-2026", nd.next_date());
    }
    @Test
    public void testNextDate_NormalDay() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2025, 3, 14);
        assertEquals("Next Date is :15-3-2025", nd.next_date());
    }
    @Test
    public void testNextDate_EndOf30DayMonth() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2025, 4, 30);
        assertEquals("Next Date is :1-5-2025", nd.next_date());
    }
    @Test
    public void testNextDate_FebNonLeapYear() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2025, 2, 28);
        assertEquals("Next Date is :1-3-2025", nd.next_date());
    }
    @Test
    public void testNextDate_FebLeapYear() {
        NextDate nd = new NextDate();
        nd.setYearmonthdate(2024, 2, 29);
        assertEquals("Next Date is :1-3-2024", nd.next_date());
    }


}
