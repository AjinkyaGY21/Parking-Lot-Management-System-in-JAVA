import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private HashMap<Vehicle.VehicleSize, HashMap<Vehicle.VehicleType, Integer>> parkingAvailableBySize;

    public ParkingLot(int numOfSmallRegularSpaces, int numOfMediumRegularSpaces, int numOfLargeRegularSpaces, int numOfSmallHandicappedSpaces, int numOfMediumHandicappedSpaces, int numOfLargeHandicappedSpaces) {
        parkingAvailableBySize = new HashMap<>();

        HashMap<Vehicle.VehicleType, Integer> smallSpaces = new HashMap<>();
        smallSpaces.put(Vehicle.VehicleType.REGULAR, numOfSmallRegularSpaces);
        smallSpaces.put(Vehicle.VehicleType.HANDICAPPED, numOfSmallHandicappedSpaces);
        parkingAvailableBySize.put(Vehicle.VehicleSize.SMALL, smallSpaces);

        HashMap<Vehicle.VehicleType, Integer> mediumSpaces = new HashMap<>();
        mediumSpaces.put(Vehicle.VehicleType.REGULAR, numOfMediumRegularSpaces);
        mediumSpaces.put(Vehicle.VehicleType.HANDICAPPED, numOfMediumHandicappedSpaces);
        parkingAvailableBySize.put(Vehicle.VehicleSize.MEDIUM, mediumSpaces);

        HashMap<Vehicle.VehicleType, Integer> largeSpaces = new HashMap<>();
        largeSpaces.put(Vehicle.VehicleType.REGULAR, numOfLargeRegularSpaces);
        largeSpaces.put(Vehicle.VehicleType.HANDICAPPED, numOfLargeHandicappedSpaces);
        parkingAvailableBySize.put(Vehicle.VehicleSize.LARGE, largeSpaces);
    }

    public ParkingSpace park(Vehicle vehicle) {
        ParkingSpace attemptedPark = tryToPark(vehicle);
        if (attemptedPark != null) {
            attemptedPark.setIsTaken(true);
            System.out.println(vehicle.getClass().getSimpleName() + " has parked in a " + attemptedPark);
        } else {
            System.out.println("The " + vehicle.getClass().getSimpleName() + " cannot be parked at this time.");
        }
        return attemptedPark;
    }

    public boolean getIsParkingLotFull() {
        for (HashMap<Vehicle.VehicleType, Integer> typeCountMap : parkingAvailableBySize.values()) {
            for (int count : typeCountMap.values()) {
                if (count > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toStringSpotsLeft() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Vehicle.VehicleSize, HashMap<Vehicle.VehicleType, Integer>> sizeEntry : parkingAvailableBySize.entrySet()) {
            Vehicle.VehicleSize size = sizeEntry.getKey();
            sb.append("Size: ").append(size).append("\n");

            for (Map.Entry<Vehicle.VehicleType, Integer> typeEntry : sizeEntry.getValue().entrySet()) {
                Vehicle.VehicleType type = typeEntry.getKey();
                int count = typeEntry.getValue();
                sb.append("Type: ").append(type).append(", Count: ").append(count).append("\n");
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    public ParkingSpace tryToPark(Vehicle vehicle) {

        Vehicle.VehicleSize size = vehicle.getSize();
    
        // Check if there are any spots available for the vehicle size
        HashMap<Vehicle.VehicleType, Integer> typeCountMap = parkingAvailableBySize.get(size);
        if (typeCountMap != null) {
            // Check if there are any handicapped spots available
            int handicappedCount = typeCountMap.getOrDefault(Vehicle.VehicleType.HANDICAPPED, 0);
            if (handicappedCount > 0 && vehicle.getType() == Vehicle.VehicleType.HANDICAPPED) {
                // Park in a handicapped spot
                typeCountMap.put(Vehicle.VehicleType.HANDICAPPED, handicappedCount - 1);
                return new ParkingSpace(size, Vehicle.VehicleType.HANDICAPPED);
            }
    
            // Check if there are any regular spots available
            int regularCount = typeCountMap.getOrDefault(Vehicle.VehicleType.REGULAR, 0);
            if (regularCount > 0) {
                // Park in a regular spot
                typeCountMap.put(Vehicle.VehicleType.REGULAR, regularCount - 1);
                return new ParkingSpace(size, Vehicle.VehicleType.REGULAR);
            }
        }
    
        // If no suitable spot is available, try to find a larger spot
        for (Map.Entry<Vehicle.VehicleSize, HashMap<Vehicle.VehicleType, Integer>> entry : parkingAvailableBySize.entrySet()) {
            Vehicle.VehicleSize largerSize = entry.getKey();
            if (largerSize.compareTo(size) > 0) {
                // Check if there are any handicapped spots available in the larger size
                HashMap<Vehicle.VehicleType, Integer> largerTypeCountMap = entry.getValue();
                int handicappedCount = largerTypeCountMap.getOrDefault(Vehicle.VehicleType.HANDICAPPED, 0);
                if (handicappedCount > 0 && vehicle.getType() == Vehicle.VehicleType.HANDICAPPED) {
                    // Park in a handicapped spot of the larger size
                    largerTypeCountMap.put(Vehicle.VehicleType.HANDICAPPED, handicappedCount - 1);
                    return new ParkingSpace(largerSize, Vehicle.VehicleType.HANDICAPPED);
                }
    
                // Check if there are any regular spots available in the larger size
                int regularCount = largerTypeCountMap.getOrDefault(Vehicle.VehicleType.REGULAR, 0);
                if (regularCount > 0) {
                    // Park in a regular spot of the larger size
                    largerTypeCountMap.put(Vehicle.VehicleType.REGULAR, regularCount - 1);
                    return new ParkingSpace(largerSize, Vehicle.VehicleType.REGULAR);
                }
            }
        }
        
        return null; // No suitable parking space available
    }    
}
/*
 In this modified implementation, the method first tries to find a suitable spot in the same size category. If a handicapped vehicle arrives, it checks if there are any handicapped spots available and parks the vehicle there. If there are no handicapped spots available or the vehicle is not handicapped, it checks for regular spots in the same size category.

If no suitable spot is found in the same size category, it searches for larger size categories. It looks for handicapped spots first and then regular spots in the larger sizes. This way, smaller vehicles have the opportunity to park in larger spots if no suitable spot is available in their size category.

This approach optimizes parking by maximizing the utilization of available spaces, accommodating different vehicle sizes, and giving priority to handicapped vehicles.
 */