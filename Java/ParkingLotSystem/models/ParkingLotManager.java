package models;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingLotManager {
    private static final Logger LOGGER = Logger.getLogger(ParkingLotManager.class.getName());
    private static final String NO_AVAILABLE_SPOT_MESSAGE = "No available spot for vehicle type: ";
    private static final String TICKET_GENERATED_MESSAGE = "Ticket generated with ID: ";
    private static final String AMOUNT_DUE_MESSAGE = "Amount due for ticket ";

    private final ParkingLot parkingLot;

    public ParkingLotManager(ParkingLot parkingLot) {
        if (parkingLot == null) {
            throw new IllegalArgumentException("ParkingLot cannot be null");
        }
        this.parkingLot = parkingLot;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            LOGGER.log(Level.WARNING, "Vehicle cannot be null");
            return null;
        }
        ParkingSpot availableSpot = parkingLot.findAvailableSpot(vehicle);
        if (availableSpot != null) {
            availableSpot.blockParkingSpot();
            Ticket ticket = new Ticket(UUID.randomUUID().toString(), vehicle, availableSpot);
            LOGGER.log(Level.INFO, TICKET_GENERATED_MESSAGE + "{0}", ticket.getTicketId());
            return ticket;
        } else {
            LOGGER.log(Level.INFO, NO_AVAILABLE_SPOT_MESSAGE + "{0}", vehicle.getType());
            return null;
        }
    }

    public void checkoutVehicle(Ticket ticket) {
        if (ticket == null) {
            LOGGER.log(Level.WARNING, "Ticket cannot be null");
            return;
        }

        ParkingSpot spot = ticket.getSpot();
        if (spot != null) {
            spot.freeParkingSpot();
            double amountDue = Payment.calculateAmount(ticket);
            LOGGER.log(Level.INFO, AMOUNT_DUE_MESSAGE + "{0}: INR{1}", new Object[]{ticket.getTicketId(), amountDue});
        } else {
            LOGGER.log(Level.WARNING, "ParkingSpot is null for ticket ID: {0}", ticket.getTicketId());
        }
    }
}