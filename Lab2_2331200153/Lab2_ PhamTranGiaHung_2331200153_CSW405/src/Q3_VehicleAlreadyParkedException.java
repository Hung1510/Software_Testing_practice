/**
 * Exception thrown when attempting to park a vehicle that is already in the garage
 */
public class Q3_VehicleAlreadyParkedException extends Exception {
    public Q3_VehicleAlreadyParkedException(String message) {
        super(message);
    }
}
