/**
 * Exception thrown when attempting to remove a vehicle that is not in the garage
 */
public class VehicleNotParkedException extends Exception {
    public VehicleNotParkedException(String message) {
        super(message);
    }
    
    public VehicleNotParkedException(String licenseNumber) {
        super("Vehicle with license number " + licenseNumber + " is not parked in the garage");
    }
}
