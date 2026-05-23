package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ride {

    private UUID rideId;

    private Driver driver;

    private Rider rider;

    private RideStatus rideStatus;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private double fare;

    public Ride(
            UUID rideId,
            Driver driver,
            Rider rider,
            RideStatus rideStatus,
            LocalDateTime startTime) {

        this.rideId = rideId;
        this.driver = driver;
        this.rider = rider;
        this.rideStatus = rideStatus;
        this.startTime = startTime;
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------

    public UUID getRideId() {
        return rideId;
    }

    public void setRideId(UUID rideId) {
        this.rideId = rideId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
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
                ", driver=" + driver.getName() +
                ", rider=" + rider.getName() +
                ", status=" + rideStatus +
                ", fare=" + fare +
                '}';
    }
}