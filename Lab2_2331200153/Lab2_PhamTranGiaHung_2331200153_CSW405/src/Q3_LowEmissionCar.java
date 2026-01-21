public class Q3_LowEmissionCar extends Q3_Car {
    private static final double LOW_EMISSION_FEE = 4.0; // Half of regular car fee
    public Q3_LowEmissionCar(String licenseNumber, int passengerCapacity) {
        super(licenseNumber, passengerCapacity);
    }
    
    @Override
    public double getFee() {
        return LOW_EMISSION_FEE;
    }
    
    @Override
    public String toString() {
        return "LowEmissionCar [" + licenseNumber + ", capacity=" + getPassengerCapacity() + "]";
    }
}
