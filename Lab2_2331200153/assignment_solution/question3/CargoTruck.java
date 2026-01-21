/**
 * Represents a cargo truck in the parking garage
 */
public class CargoTruck extends Vehicle {
    private int grossWeight; // in pounds
    private static final double FEE_PER_10000_LBS = 10.0;
    
    /**
     * Constructor for CargoTruck
     * @param licenseNumber The license plate number
     * @param grossWeight Gross vehicle weight in pounds
     */
    public CargoTruck(String licenseNumber, int grossWeight) {
        super(licenseNumber);
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("Gross weight must be positive");
        }
        this.grossWeight = grossWeight;
    }
    
    /**
     * Get the gross vehicle weight
     * @return weight in pounds
     */
    public int getGrossWeight() {
        return grossWeight;
    }
    
    @Override
    public int getSpacesRequired() {
        // Trucks occupy grossWeight / 10,000 spaces (rounded up)
        return (int) Math.ceil(grossWeight / 10000.0);
    }
    
    @Override
    public double getFee() {
        // $10 per 10,000 pounds
        return (grossWeight / 10000.0) * FEE_PER_10000_LBS;
    }
    
    @Override
    public String toString() {
        return "CargoTruck [" + licenseNumber + ", weight=" + grossWeight + "lbs]";
    }
}
