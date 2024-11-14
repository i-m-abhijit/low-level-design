import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Bike;
import models.Car;
import models.ParkingLot;
import models.ParkingLotManager;
import models.ParkingSpot;
import models.Ticket;
import models.Vehicle;
import models.VehicleType;

public class Main {
    public static void main(String[] args) {
        // Initialize parking spots with distances
        List<ParkingSpot> bikeSpots = new ArrayList<>();
        List<ParkingSpot> carSpots = new ArrayList<>();

        // Add spots with increasing distances
        for (int i = 0; i < 10; i++) bikeSpots.add(new ParkingSpot("B" + i, VehicleType.BIKE, i + 1));
        for (int i = 0; i < 10; i++) carSpots.add(new ParkingSpot("C" + i, VehicleType.CAR, i + 1));

        // Map the spots by size
        Map<VehicleType, List<ParkingSpot>> parkingSpots = new HashMap<>();
        parkingSpots.put(VehicleType.BIKE, bikeSpots);
        parkingSpots.put(VehicleType.CAR, carSpots);

        ParkingLot parkingLot = new ParkingLot(parkingSpots);
        ParkingLotManager manager = new ParkingLotManager(parkingLot);

        // Park vehicles and find the closest available spots
        Vehicle car1 = new Car("CAR123");
        Vehicle car2 = new Car("CAR456");
        Vehicle bike = new Bike("BIKE789");

        Ticket ticket1 = manager.parkVehicle(car1);  // Should get closest medium spot
        Ticket ticket2 = manager.parkVehicle(bike);  // Should get closest small spot
        Ticket ticket3 = manager.parkVehicle(car2);  // Should get next closest medium spot

        // Checkout vehicles
        manager.checkoutVehicle(ticket1);
        manager.checkoutVehicle(ticket2);
        manager.checkoutVehicle(ticket3);
    }
}
