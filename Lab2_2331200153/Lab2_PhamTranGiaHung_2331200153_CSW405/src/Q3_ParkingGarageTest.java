import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Q3_ParkingGarageTest {
    private Q3_ParkingGarage garage;
    private Q3_Car car1;
    private Q3_Car car2;
    private Q3_LowEmissionCar eco1;
    private Q3_Truck truck1;
    private Q3_Truck truck2;

    @Before
    public void setUp() {
        // Create garage with 10 spaces
        garage = new Q3_ParkingGarage(10);

        // Create test vehicles
        car1 = new Q3_Car("ABC123", 5);
        car2 = new Q3_Car("DEF456", 4);
        eco1 = new Q3_LowEmissionCar("ENDFIELD001", 4);
        truck1 = new Q3_Truck("TOMORROW002", 25000); // 3 spaces
        truck2 = new Q3_Truck("SOMETHING003", 50000); // 5 spaces
    }

    @Test
    public void testCarEntry() throws Exception {
        garage.enter(car1);

        assertEquals(9, garage.getAvailableSpaces());
        assertEquals(1, garage.getNumberOfVehicles());
        assertEquals(8.0, garage.getTotalRevenue(), 0.01);
        assertTrue(garage.isVehicleParked("ABC123"));
    }

    @Test
    public void testLowEmissionCarFee() throws Exception {
        garage.enter(eco1);

        assertEquals(9, garage.getAvailableSpaces());
        assertEquals(4.0, garage.getTotalRevenue(), 0.01);
    }

    @Test
    public void testTruckSpaceCalculation() throws Exception {
        // Truck  25000 lbs  take 3 spaces, 2,5 round up
        garage.enter(truck1);

        assertEquals(7, garage.getAvailableSpaces()); // 10 - 3
        assertEquals(3, truck1.getSpacesRequired());
    }

    @Test
    public void testTruckFeeCalculation() throws Exception {
        // Truck with 25000lbs: (25000/10000) * 10 = 25
        garage.enter(truck1);
        assertEquals(25.0, garage.getTotalRevenue(), 0.01);

        // Truck with 50000 lbs: (50000/10000) * 10 = 50
        Q3_ParkingGarage garage2 = new Q3_ParkingGarage(10);
        garage2.enter(truck2);
        assertEquals(50.0, garage2.getTotalRevenue(), 0.01);
    }

    @Test(expected = Q3_InsufficientSpaceException.class)
    public void testInsufficientSpace() throws Exception {
        // Fill garage to 9 space
        garage.enter(car1); // 1
        garage.enter(car2); // 1
        garage.enter(eco1); // 1
        garage.enter(new Q3_Car("CAR3", 5)); // 1
        garage.enter(new Q3_Car("CAR4", 5)); // 1
        garage.enter(new Q3_Car("CAR5", 5)); // 1
        garage.enter(new Q3_Car("CAR6", 5)); // 1
        garage.enter(new Q3_Car("CAR7", 5)); // 1
        garage.enter(new Q3_Car("CAR8", 5)); // 1

        assertEquals(1, garage.getAvailableSpaces());

        // Try truck that need 3, fail
        garage.enter(truck1);
    }

    @Test(expected = Q3_VehicleAlreadyParkedException.class)
    public void testDuplicateEntry() throws Exception {
        garage.enter(car1);
        garage.enter(car1); // throw exception
    }

    @Test
    public void testVehicleExit() throws Exception {
        garage.enter(car1);
        garage.enter(car2);

        assertEquals(8, garage.getAvailableSpaces());
        assertEquals(2, garage.getNumberOfVehicles());

        // Exit car1
        garage.exit("ABC123");

        assertEquals(9, garage.getAvailableSpaces());
        assertEquals(1, garage.getNumberOfVehicles());
        assertFalse(garage.isVehicleParked("ABC123"));
        assertTrue(garage.isVehicleParked("DEF456"));

        // Revenue not change on exit
        assertEquals(16.0, garage.getTotalRevenue(), 0.01);
    }

    @Test(expected = Q3_VehicleNotParkedException.class)
    public void testExitNonExistentVehicle() throws Exception {
        garage.exit("lollol");
    }

    @Test
    public void testTotalRevenue() throws Exception {
        double expectedRevenue = 0.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);

        garage.enter(car1);
        expectedRevenue += 8.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);

        garage.enter(eco1);
        expectedRevenue += 4.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);

        garage.enter(truck1);
        expectedRevenue += 25.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);

        // Exit not change revenue
        garage.exit("ABC123");
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);
    }

    @Test
    public void testMultipleVehicleTypes() throws Exception {
        garage.enter(car1);      // 1 space, 8
        garage.enter(eco1);      // 1 space, 4
        garage.enter(truck1);    // 3 spaces, 25

        // Verify state
        assertEquals(5, garage.getAvailableSpaces()); // 10 - 1 - 1 - 3
        assertEquals(3, garage.getNumberOfVehicles());
        assertEquals(37.0, garage.getTotalRevenue(), 0.01); // 8 + 4 + 25

        // Exit eco car
        garage.exit("ENDFIELD001");
        assertEquals(6, garage.getAvailableSpaces());
        assertEquals(2, garage.getNumberOfVehicles());

        // Enter another truck
        garage.enter(truck2);    // 5 spaces, 50
        assertEquals(1, garage.getAvailableSpaces()); // 6 - 5
        assertEquals(87.0, garage.getTotalRevenue(), 0.01); // 37 + 50
    }

    @Test
    public void testGetVehicle() throws Exception {
        garage.enter(car1);

        Q3_Vehicle retrieved = garage.getVehicle("ABC123");
        assertNotNull(retrieved);
        assertEquals(car1, retrieved);
        assertEquals("ABC123", retrieved.getLicenseNumber());

        Q3_Vehicle notFound = garage.getVehicle("lollol");
        assertNull(notFound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullVehicleEntry() throws Exception {
        garage.enter(null);
    }
}
