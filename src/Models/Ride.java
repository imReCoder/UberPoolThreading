package Models;

import Util.IdGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ride {

    private final Location sourceLocation;
    private final Location destinationLocation;
    private final Location currentLocation;
    private UUID rideId;

    private UUID driverId;

    private UUID riderId;

    private RideStatus rideStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double fare;

    public Ride(
            UUID driverId,
            UUID riderId,
            Location sourceLocation,
            Location destinationLocation
    ) {

        this.rideId = IdGenerator.generateId();
        this.driverId = driverId;
        this.riderId = riderId;
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.rideStatus = RideStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------

    public UUID getRideId() {
        return rideId;
    }

    public Location getSourceLocation() {
        return sourceLocation;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public UUID getDriverId() {
        return driverId;
    }


    public UUID getRiderId() {
        return riderId;
    }

    public void setStatus(RideStatus status){
        this.rideStatus = status;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(
            LocalDateTime startTime) {

        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(
            LocalDateTime endTime) {

        this.endTime = endTime;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }


    // ----------------------------
    // Helper Methods
    // ----------------------------

    public boolean isCompleted() {
        return rideStatus == RideStatus.COMPLETED;
    }

    public boolean isStarted() {
        return rideStatus == RideStatus.STARTED;
    }

    @Override
    public String toString() {

        return "Ride{" +
                "rideId='" + rideId + '\'' +
                ", driver=" + driverId +
                ", rider=" + riderId +
                ", status=" + rideStatus +
                ", fare=" + fare +
                '}';
    }
}