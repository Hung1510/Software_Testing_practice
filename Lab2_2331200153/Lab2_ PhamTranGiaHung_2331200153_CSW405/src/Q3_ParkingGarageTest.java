import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Comprehensive test suite for ParkingGarage system following TDD methodology
 */
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
        car2 = new Q3_Car("XYZ789", 4);
        eco1 = new Q3_LowEmissionCar("ECO001", 4);
        truck1 = new Q3_Truck("TRK100", 25000); // 3 spaces
        truck2 = new Q3_Truck("TRK200", 50000); // 5 spaces
    }
    
    @Test
    public void testGarageInitialization() {
        assertEquals(10, garage.getTotalCapacity());
        assertEquals(10, garage.getAvailableSpaces());
        assertEquals(0, garage.getNumberOfVehicles());
        assertEquals(0.0, garage.getTotalRevenue(), 0.01);
    }
    
    @Test
    public void testCarEntry() throws Exception {
        garage.enter(car1);
        
        assertEquals(9, garage.getAvailableSpaces());
        assertEquals(1, garage.getNumberOfVehicles());
        assertEquals(8.0, garage.getTotalRevenue(), 0.01); // Car fee is $8
        assertTrue(garage.isVehicleParked("ABC123"));
    }
    
    @Test
    public void testLowEmissionCarFee() throws Exception {
        garage.enter(eco1);
        
        assertEquals(9, garage.getAvailableSpaces());
        assertEquals(4.0, garage.getTotalRevenue(), 0.01); // Low-emission fee is $4
    }
    
    @Test
    public void testTruckSpaceCalculation() throws Exception {
        // Truck with 25000 lbs should take 3 spaces (25000/10000 = 2.5, rounded up)
        garage.enter(truck1);
        
        assertEquals(7, garage.getAvailableSpaces()); // 10 - 3 = 7
        assertEquals(3, truck1.getSpacesRequired());
    }
    
    @Test
    public void testTruckFeeCalculation() throws Exception {
        // Truck with 25000 lbs: (25000/10000) * $10 = $25
        garage.enter(truck1);
        assertEquals(25.0, garage.getTotalRevenue(), 0.01);
        
        // Truck with 50000 lbs: (50000/10000) * $10 = $50
        Q3_ParkingGarage garage2 = new Q3_ParkingGarage(10);
        garage2.enter(truck2);
        assertEquals(50.0, garage2.getTotalRevenue(), 0.01);
    }
    
    @Test(expected = Q3_InsufficientSpaceException.class)
    public void testInsufficientSpace() throws Exception {
        // Fill garage to 9 spaces
        garage.enter(car1); // 1 space
        garage.enter(car2); // 1 space
        garage.enter(eco1); // 1 space
        garage.enter(new Q3_Car("CAR3", 5)); // 1 space
        garage.enter(new Q3_Car("CAR4", 5)); // 1 space
        garage.enter(new Q3_Car("CAR5", 5)); // 1 space
        garage.enter(new Q3_Car("CAR6", 5)); // 1 space
        garage.enter(new Q3_Car("CAR7", 5)); // 1 space
        garage.enter(new Q3_Car("CAR8", 5)); // 1 space
        
        assertEquals(1, garage.getAvailableSpaces());
        
        // Try to enter truck that needs 3 spaces - should fail
        garage.enter(truck1);
    }
    
    @Test(expected = Q3_VehicleAlreadyParkedException.class)
    public void testDuplicateEntry() throws Exception {
        garage.enter(car1);
        garage.enter(car1); // Should throw exception
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
        assertTrue(garage.isVehicleParked("XYZ789"));
        
        // Revenue should not change on exit
        assertEquals(16.0, garage.getTotalRevenue(), 0.01);
    }
    
    @Test(expected = Q3_VehicleNotParkedException.class)
    public void testExitNonExistentVehicle() throws Exception {
        garage.exit("NOTHERE");
    }
    
    @Test
    public void testAvailableSpaces() throws Exception {
        assertEquals(10, garage.getAvailableSpaces());
        
        garage.enter(car1); // -1 space
        assertEquals(9, garage.getAvailableSpaces());
        
        garage.enter(truck1); // -3 spaces
        assertEquals(6, garage.getAvailableSpaces());
        
        garage.enter(car2); // -1 space
        assertEquals(5, garage.getAvailableSpaces());
        
        garage.exit("ABC123"); // +1 space
        assertEquals(6, garage.getAvailableSpaces());
        
        garage.exit("TRK100"); // +3 spaces
        assertEquals(9, garage.getAvailableSpaces());
    }
    
    @Test
    public void testTotalRevenue() throws Exception {
        double expectedRevenue = 0.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);
        
        garage.enter(car1); // +$8
        expectedRevenue += 8.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);
        
        garage.enter(eco1); // +$4
        expectedRevenue += 4.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);
        
        garage.enter(truck1); // +$25
        expectedRevenue += 25.0;
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);
        
        // Exit should not change revenue
        garage.exit("ABC123");
        assertEquals(expectedRevenue, garage.getTotalRevenue(), 0.01);
    }
    
    @Test
    public void testMultipleVehicleTypes() throws Exception {
        // Park various vehicle types
        garage.enter(car1);      // 1 space, $8
        garage.enter(eco1);      // 1 space, $4
        garage.enter(truck1);    // 3 spaces, $25
        
        // Verify state
        assertEquals(5, garage.getAvailableSpaces()); // 10 - 1 - 1 - 3 = 5
        assertEquals(3, garage.getNumberOfVehicles());
        assertEquals(37.0, garage.getTotalRevenue(), 0.01); // 8 + 4 + 25 = 37
        
        // Exit eco car
        garage.exit("ECO001");
        assertEquals(6, garage.getAvailableSpaces());
        assertEquals(2, garage.getNumberOfVehicles());
        
        // Enter another truck
        garage.enter(truck2);    // 5 spaces, $50
        assertEquals(1, garage.getAvailableSpaces()); // 6 - 5 = 1
        assertEquals(87.0, garage.getTotalRevenue(), 0.01); // 37 + 50 = 87
    }
    
    @Test
    public void testGetVehicle() throws Exception {
        garage.enter(car1);
        
        Q3_Vehicle retrieved = garage.getVehicle("ABC123");
        assertNotNull(retrieved);
        assertEquals(car1, retrieved);
        assertEquals("ABC123", retrieved.getLicenseNumber());
        
        Q3_Vehicle notFound = garage.getVehicle("NOTHERE");
        assertNull(notFound);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullVehicleEntry() throws Exception {
        garage.enter(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullLicenseExit() throws Exception {
        garage.exit(null);
    }
    
    @Test
    public void testVehicleCreation() {
        // Test Car
        Q3_Car car = new Q3_Car("TEST1", 5);
        assertEquals("TEST1", car.getLicenseNumber());
        assertEquals(5, car.getPassengerCapacity());
        assertEquals(1, car.getSpacesRequired());
        assertEquals(8.0, car.getFee(), 0.01);
        
        // Test LowEmissionCar
        Q3_LowEmissionCar eco = new Q3_LowEmissionCar("TEST2", 4);
        assertEquals("TEST2", eco.getLicenseNumber());
        assertEquals(4, eco.getPassengerCapacity());
        assertEquals(1, eco.getSpacesRequired());
        assertEquals(4.0, eco.getFee(), 0.01);
        
        // Test CargoTruck
        Q3_Truck truck = new Q3_Truck("TEST3", 30000);
        assertEquals("TEST3", truck.getLicenseNumber());
        assertEquals(30000, truck.getGrossWeight());
        assertEquals(3, truck.getSpacesRequired());
        assertEquals(30.0, truck.getFee(), 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCarCapacity() {
        new Q3_Car("TEST", 0); // Should throw exception
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTruckWeight() {
        new Q3_Truck("TEST", -1000); // Should throw exception
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGarageCapacity() {
        new Q3_ParkingGarage(0); // Should throw exception
    }
    
    @Test
    public void testComplexScenario() throws Exception {
        // Simulate a busy day at the garage
        Q3_ParkingGarage busyGarage = new Q3_ParkingGarage(20);
        
        // Morning rush - cars arriving
        busyGarage.enter(new Q3_Car("CAR001", 5));
        busyGarage.enter(new Q3_Car("CAR002", 4));
        busyGarage.enter(new Q3_LowEmissionCar("ECO001", 2));
        busyGarage.enter(new Q3_LowEmissionCar("ECO002", 4));
        
        assertEquals(16, busyGarage.getAvailableSpaces());
        assertEquals(24.0, busyGarage.getTotalRevenue(), 0.01); // 8+8+4+4
        
        // Delivery trucks arrive
        busyGarage.enter(new Q3_Truck("TRK001", 20000)); // 2 spaces, $20
        busyGarage.enter(new Q3_Truck("TRK002", 35000)); // 4 spaces, $35
        
        assertEquals(10, busyGarage.getAvailableSpaces());
        assertEquals(79.0, busyGarage.getTotalRevenue(), 0.01);
        
        // Some vehicles leave
        busyGarage.exit("CAR001");
        busyGarage.exit("TRK001");
        
        assertEquals(13, busyGarage.getAvailableSpaces());
        
        // More vehicles arrive
        busyGarage.enter(new Q3_Car("CAR003", 5));
        busyGarage.enter(new Q3_Car("CAR004", 3));
        
        assertEquals(11, busyGarage.getAvailableSpaces());
        assertEquals(95.0, busyGarage.getTotalRevenue(), 0.01); // Previous + 8 + 8
    }
}
