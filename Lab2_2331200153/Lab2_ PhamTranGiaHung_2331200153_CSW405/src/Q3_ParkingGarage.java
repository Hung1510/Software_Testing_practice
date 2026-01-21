import java.util.HashMap;
import java.util.Map;

/**
 * ParkingGarage manages vehicle parking with space tracking and fee collection
 */
public class Q3_ParkingGarage {
    private int totalCapacity;
    private Map<String, Q3_Vehicle> parkedVehicles;
    private double totalRevenue;
    
    /**
     * Constructor for ParkingGarage
     * @param totalCapacity Total number of parking spaces
     */
    public Q3_ParkingGarage(int totalCapacity) {
        if (totalCapacity <= 0) {
            throw new IllegalArgumentException("Total capacity must be positive");
        }
        this.totalCapacity = totalCapacity;
        this.parkedVehicles = new HashMap<>();
        this.totalRevenue = 0.0;
    }
    
    /**
     * Process a vehicle entering the garage
     * @param vehicle The vehicle entering
     * @throws Q3_InsufficientSpaceException If not enough space available
     * @throws Q3_VehicleAlreadyParkedException If vehicle is already in garage
     */
    public void enter(Q3_Vehicle vehicle) throws Q3_InsufficientSpaceException, Q3_VehicleAlreadyParkedException {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        
        // Check if vehicle already parked
        if (parkedVehicles.containsKey(vehicle.getLicenseNumber())) {
            throw new Q3_VehicleAlreadyParkedException(vehicle.getLicenseNumber());
        }
        
        // Check if enough space available
        int spacesNeeded = vehicle.getSpacesRequired();
        if (getAvailableSpaces() < spacesNeeded) {
            throw new Q3_InsufficientSpaceException(
                "Not enough space. Need " + spacesNeeded + " spaces, but only " + 
                getAvailableSpaces() + " available"
            );
        }
        
        // Park the vehicle and collect fee
        parkedVehicles.put(vehicle.getLicenseNumber(), vehicle);
        totalRevenue += vehicle.getFee();
    }
    
    /**
     * Process a vehicle exiting the garage
     * @param licenseNumber License number of the vehicle leaving
     * @throws Q3_VehicleNotParkedException If vehicle is not in the garage
     */
    public void exit(String licenseNumber) throws Q3_VehicleNotParkedException {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License number cannot be null or empty");
        }
        
        // Check if vehicle is in garage
        if (!parkedVehicles.containsKey(licenseNumber)) {
            throw new Q3_VehicleNotParkedException(licenseNumber);
        }
        
        // Remove vehicle from garage
        parkedVehicles.remove(licenseNumber);
    }
    
    /**
     * Get the number of available parking spaces
     * @return number of available spaces
     */
    public int getAvailableSpaces() {
        int occupiedSpaces = 0;
        for (Q3_Vehicle vehicle : parkedVehicles.values()) {
            occupiedSpaces += vehicle.getSpacesRequired();
        }
        return totalCapacity - occupiedSpaces;
    }
    
    /**
     * Get the total capacity of the garage
     * @return total number of spaces
     */
    public int getTotalCapacity() {
        return totalCapacity;
    }
    
    /**
     * Get the total revenue collected from parking fees
     * @return total revenue in dollars
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }
    
    /**
     * Get the number of vehicles currently parked
     * @return number of vehicles
     */
    public int getNumberOfVehicles() {
        return parkedVehicles.size();
    }
    
    /**
     * Check if a specific vehicle is parked in the garage
     * @param licenseNumber License number to check
     * @return true if vehicle is parked, false otherwise
     */
    public boolean isVehicleParked(String licenseNumber) {
        return parkedVehicles.containsKey(licenseNumber);
    }
    
    /**
     * Get a parked vehicle by license number
     * @param licenseNumber License number
     * @return Vehicle object or null if not found
     */
    public Q3_Vehicle getVehicle(String licenseNumber) {
        return parkedVehicles.get(licenseNumber);
    }
    
    @Override
    public String toString() {
        return "ParkingGarage [Capacity=" + totalCapacity + 
               ", Available=" + getAvailableSpaces() + 
               ", Vehicles=" + parkedVehicles.size() + 
               ", Revenue=$" + String.format("%.2f", totalRevenue) + "]";
    }
}
