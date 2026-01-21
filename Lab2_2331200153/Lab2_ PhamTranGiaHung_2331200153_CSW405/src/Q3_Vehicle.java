/**
 * Abstract base class for all vehicles
 */
public abstract class Q3_Vehicle {
    protected String licenseNumber;
    
    /**
     * Constructor for Vehicle
     * @param licenseNumber The license plate number
     */
    public Q3_Vehicle(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License number cannot be null or empty");
        }
        this.licenseNumber = licenseNumber.trim();
    }
    
    /**
     * Get the license number of this vehicle
     * @return license number
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    /**
     * Get the number of parking spaces this vehicle requires
     * @return number of spaces
     */
    public abstract int getSpacesRequired();
    
    /**
     * Get the parking fee for this vehicle
     * @return fee in dollars
     */
    public abstract double getFee();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Q3_Vehicle vehicle = (Q3_Vehicle) obj;
        return licenseNumber.equals(vehicle.licenseNumber);
    }
    
    @Override
    public int hashCode() {
        return licenseNumber.hashCode();
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + licenseNumber + "]";
    }
}
