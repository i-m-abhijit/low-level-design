package models;

public class ParkingSpot {
    private final String spotId;
    private boolean isAvailable;
    private final VehicleType type;
    private final int distance;
    public ParkingSpot(String spotId, VehicleType type, int distance){
        this.spotId = spotId;
        this.type = type;
        this.distance = distance;
        this.isAvailable = true;
    }
    public String getSpotId() {
        return spotId;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getDistance() {
        return distance;
    }

    public void blockParkingSpot(){
        isAvailable = false;
    }

    public void freeParkingSpot(){
        isAvailable = true;
    }
}
