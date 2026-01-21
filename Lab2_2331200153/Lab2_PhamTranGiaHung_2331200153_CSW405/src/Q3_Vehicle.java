//test
public abstract class Q3_Vehicle {
    protected String licenseNumber;
    
    //Constructor
    public Q3_Vehicle(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License cant be null or empty");
        }
        this.licenseNumber = licenseNumber.trim();
    }
    
    //Get  license number
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    //Get number of parking space thr vec need
    public abstract int getSpacesRequired();
    
    //Get parking fee for vehicle
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
