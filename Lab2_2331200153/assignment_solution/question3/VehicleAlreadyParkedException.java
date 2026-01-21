/**
 * Exception thrown when attempting to park a vehicle that is already in the garage
 */
public class VehicleAlreadyParkedException extends Exception {
    public VehicleAlreadyParkedException(String message) {
        super(message);
    }
    
    public VehicleAlreadyParkedException(String licenseNumber) {
        super("Vehicle with license number " + licenseNumber + " is already parked in the garage");
    }
}
