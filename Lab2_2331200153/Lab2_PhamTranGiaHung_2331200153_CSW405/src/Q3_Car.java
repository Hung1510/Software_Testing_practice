public class Q3_Car extends Q3_Vehicle {
    private int passengerCapacity;
    private static final double PARKING_FEE = 8.0;
    private static final int SPACES_REQUIRED = 1;
    
    //Constructor
    public Q3_Car(String licenseNumber, int passengerCapacity) {
        super(licenseNumber);
        if (passengerCapacity <= 0) {
            throw new IllegalArgumentException("Passenger capacity must be positive");
        }
        this.passengerCapacity = passengerCapacity;
    }
    
    //passenger capacity
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
