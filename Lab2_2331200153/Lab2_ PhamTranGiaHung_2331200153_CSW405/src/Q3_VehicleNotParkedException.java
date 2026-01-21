/**
 * Exception thrown when attempting to remove a vehicle that is not in the garage
 */
public class Q3_VehicleNotParkedException extends Exception {
    public Q3_VehicleNotParkedException(String message) {
        super(message);
    }
}
