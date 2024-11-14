package models;

import java.util.UUID;

public class ParkingLotManager {
    private final ParkingLot parkingLot;

    public ParkingLotManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot availableSpot = parkingLot.findAvailableSpot(vehicle);
        if (availableSpot != null) {
            availableSpot.blockParkingSpot();
            Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, availableSpot);
            System.out.println("Ticket generated with ID: " + ticket.getTicketId());
            return ticket;
        } else {
            System.out.println("No available spot for vehicle type: " + vehicle.getType());
            return null;
        }
    }

    public void checkoutVehicle(Ticket ticket) {
        ticket.getSpot().freeParkingSpot();
        double amountDue = Payment.calculateAmount(ticket);
        System.out.println("Amount due for ticket " + ticket.getTicketId() + ": INR" + amountDue);
    }
}
