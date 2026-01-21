/**
 * Exception throw when trying to park car that already parked
 */
public class Q3_VehicleAlreadyParkedException extends Exception {
    public Q3_VehicleAlreadyParkedException(String message) {
        super(message);
    }
}
