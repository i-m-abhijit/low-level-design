package models;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private Map<VehicleType, List<ParkingSpot>> parkingSpots;

    public ParkingLot(Map<VehicleType, List<ParkingSpot>> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle) {
        VehicleType requiredSize = vehicle.getType();
        List<ParkingSpot> spots = parkingSpots.get(requiredSize);

        // Filter available spots and sort by distance
        List<ParkingSpot> availableSpots = spots.stream()
                .filter(ParkingSpot::isAvailable)
                .sorted((s1, s2) -> Integer.compare(s1.getDistance(), s2.getDistance()))
                .collect(Collectors.toList());

        return availableSpots.isEmpty() ? null : availableSpots.get(0); // Return closest available spot
    }
}
