public class Q3_Truck extends Q3_Vehicle {
    private int grossWeight; // pound
    private static final double Fee_10000lbs = 10.0;
    
    //Constructor
    public Q3_Truck(String licenseNumber, int grossWeight) {
        super(licenseNumber);
        if (grossWeight <= 0) {
            throw new IllegalArgumentException("weight must be positive");
        }
        this.grossWeight = grossWeight;
    }
    
    // weight
    public int getGrossWeight() {
        return grossWeight;
    }
    
    @Override
    public int getSpacesRequired() {
        return (int) Math.ceil(grossWeight / 10000.0);
    }
    
    @Override
    public double getFee() {
        // 10 dollar = 10000 pounds
        return (grossWeight / 10000.0) * Fee_10000lbs;
    }
    
    @Override
    public String toString() {
        return "CargoTruck [" + licenseNumber + ", weight=" + grossWeight + "lbs]";
    }
}
