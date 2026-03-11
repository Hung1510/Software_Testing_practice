import static org.mockito.Mockito.*;

import CustomerPremium.CustomerPremium.Customer;
import CustomerPremium.CustomerPremium.CustomerDao;
import CustomerPremium.CustomerPremium.CustomerPremium;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CustomerPremiumTest {

    CustomerDao dao;
    CustomerPremium premium;

    @Before
    public void setUp() {
        dao = mock(CustomerDao.class);
        premium = new CustomerPremium();
    }

    // Premium discount(20% off)

    @Test
    public void testPremiumCustomerGets20PercentDiscount() {
        // previousBill > 3000 AND orders >= 5 -> qualifies
        List<Customer> customers = List.of(new Customer("Dat", 4000, 6, 4000, ""));
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals(3200, customers.get(0).getPresentsBill(), 0.01);
    }

    @Test
    public void testPremiumCustomerTagIsSet() {
        List<Customer> customers = List.of(new Customer("Dat", 4000, 6, 4000, ""));
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals("Premium Customer", customers.get(0).getTag());
    }

    @Test
    public void testExactly5OrdersAndBillOver3000Qualifies() {
        //boundary: exactly 5 orders, bill just above 3000
        List<Customer> customers = List.of(new Customer("Binh", 3500, 5, 3500, ""));
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals(2800, customers.get(0).getPresentsBill(), 0.01);
        assertEquals("Premium Customer", customers.get(0).getTag());
    }

    //No premium: no discount applied

    @Test
    public void testLowBillAndLowOrdersNoDiscount() {
        // previousBill < 3000 AND orders < 5
        List<Customer> customers = List.of(new Customer("An", 2000, 3, 2000, ""));
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals(2000, customers.get(0).getPresentsBill(), 0.01);
    }

    @Test
    public void testHighBillButTooFewOrdersNoDiscount() {
        // previousBill > 3000 but orders < 5 -> not qualify
        List<Customer> customers = List.of(new Customer("Cat", 5000, 4, 5000, ""));
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals(5000, customers.get(0).getPresentsBill(), 0.01);
        assertNotEquals("Premium Customer", customers.get(0).getTag());
    }

    @Test
    public void testEnoughOrdersButBillTooLowNoDiscount() {
        // orders >= 5 but previousBill <= 3000 -> not qualify
        List<Customer> customers = List.of(new Customer("Duc", 3000, 5, 3000, ""));
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals(3000, customers.get(0).getPresentsBill(), 0.01);
        assertNotEquals("Premium Customer", customers.get(0).getTag());
    }

    @Test
    public void testNonPremiumTagUnchanged() {
        List<Customer> customers = List.of(new Customer("An", 2000, 3, 2000, "Regular"));
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals("Regular", customers.get(0).getTag());
    }

    //Mixed list

    @Test
    public void testMixedListOnlyPremiumCustomersDiscounted() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Dat",  4000, 6, 4000, "")); // qualifies
        customers.add(new Customer("An",   2000, 3, 2000, "")); // does not
        customers.add(new Customer("Binh", 3500, 5, 3500, "")); // qualifies
        when(dao.all()).thenReturn(customers);

        premium.PremiumCal(dao.all());

        assertEquals(3200, customers.get(0).getPresentsBill(), 0.01);
        assertEquals(2000, customers.get(1).getPresentsBill(), 0.01); // unchanged
        assertEquals(2800, customers.get(2).getPresentsBill(), 0.01);
    }

    //Empty list

    @Test
    public void testEmptyListNoException() {
        when(dao.all()).thenReturn(new ArrayList<>());
        premium.PremiumCal(dao.all()); // should not throw
    }
}