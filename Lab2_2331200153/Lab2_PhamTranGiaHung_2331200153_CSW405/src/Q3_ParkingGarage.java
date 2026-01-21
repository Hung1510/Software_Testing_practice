import java.util.HashMap;
import java.util.Map;

public class Q3_ParkingGarage {
    private int totalCapacity;
    private Map<String, Q3_Vehicle> parkedVehicles;
    private double totalRevenue;
    
    //constructor
    public Q3_ParkingGarage(int totalCapacity) {
        if (totalCapacity <= 0) {
            throw new IllegalArgumentException("Total capacity must be positive");
        }
        this.totalCapacity = totalCapacity;
        this.parkedVehicles = new HashMap<>();
        this.totalRevenue = 0.0;
    }
    
    /**
     * vehicle entering the garage process
     * @throws Q3_InsufficientSpaceException If not enough space available
     * @throws Q3_VehicleAlreadyParkedException If vehicle is already in garage
     */
    public void enter(Q3_Vehicle vehicle) throws Q3_InsufficientSpaceException, Q3_VehicleAlreadyParkedException {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        
        // Check if already parked
        if (parkedVehicles.containsKey(vehicle.getLicenseNumber())) {
            throw new Q3_VehicleAlreadyParkedException(vehicle.getLicenseNumber());
        }
        
        // Check if space available
        int spacesNeeded = vehicle.getSpacesRequired();
        if (getAvailableSpaces() < spacesNeeded) {
            throw new Q3_InsufficientSpaceException(
                "Not enough space. Need " + spacesNeeded + " spaces, but only " + 
                getAvailableSpaces() + " available"
            );
        }
        
        // Park vehicle and collect fee
        parkedVehicles.put(vehicle.getLicenseNumber(), vehicle);
        totalRevenue += vehicle.getFee();
    }
    
    /**
     * Process a vehicle exiting garage
     * @throws Q3_VehicleNotParkedException If vehicle not in the garage
     */
    public void exit(String licenseNumber) throws Q3_VehicleNotParkedException {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License cant be null or empty");
        }
        
        // Check if vehicle is in garage
        if (!parkedVehicles.containsKey(licenseNumber)) {
            throw new Q3_VehicleNotParkedException(licenseNumber);
        }
        
        // Remove vehicle from garage
        parkedVehicles.remove(licenseNumber);
    }
    
    //Get number of available parking space
    public int getAvailableSpaces() {
        int occupiedSpaces = 0;
        for (Q3_Vehicle vehicle : parkedVehicles.values()) {
            occupiedSpaces += vehicle.getSpacesRequired();
        }
        return totalCapacity - occupiedSpaces;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public int getNumberOfVehicles() {
        return parkedVehicles.size();
    }
    public boolean isVehicleParked(String licenseNumber) {
        return parkedVehicles.containsKey(licenseNumber);
    }
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
