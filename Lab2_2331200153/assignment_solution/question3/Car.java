/**
 * Represents a regular car in the parking garage
 */
public class Car extends Vehicle {
    private int passengerCapacity;
    private static final double PARKING_FEE = 8.0;
    private static final int SPACES_REQUIRED = 1;
    
    /**
     * Constructor for Car
     * @param licenseNumber The license plate number
     * @param passengerCapacity Number of passengers the car can hold
     */
    public Car(String licenseNumber, int passengerCapacity) {
        super(licenseNumber);
        if (passengerCapacity <= 0) {
            throw new IllegalArgumentException("Passenger capacity must be positive");
        }
        this.passengerCapacity = passengerCapacity;
    }
    
    /**
     * Get the passenger capacity
     * @return number of passengers
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    
    @Override
    public int getSpacesRequired() {
        return SPACES_REQUIRED;
    }
    
    @Override
    public double getFee() {
        return PARKING_FEE;
    }
    
    @Override
    public String toString() {
        return "Car [" + licenseNumber + ", capacity=" + passengerCapacity + "]";
    }
}
