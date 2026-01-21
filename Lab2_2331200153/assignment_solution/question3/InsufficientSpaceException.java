/**
 * Exception thrown when there is insufficient space in the parking garage
 */
public class InsufficientSpaceException extends Exception {
    public InsufficientSpaceException(String message) {
        super(message);
    }
    
    public InsufficientSpaceException() {
        super("Insufficient space available in the parking garage");
    }
}
