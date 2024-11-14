package models;

import java.time.Duration;
import java.time.LocalDateTime;

public class Payment {
    private static final double RATE_PER_HOUR = 5.0;
    public static double calculateAmount(Ticket ticket){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(ticket.getEntryTime(), now);
        long hours = duration.toHours();
        return hours * RATE_PER_HOUR;
    }
}
