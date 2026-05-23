package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class RideRequest {

    private final UUID requestId;


    private final LocalDateTime requestTime;
    private final Location source;
    private final Location destination;
    private final UUID riderId;

    private RideRequestStatus status;

    public RideRequest(Location source , Location destination , UUID riderId) {

        this.requestId = UUID.randomUUID();


        this.requestTime = LocalDateTime.now();
        this.source = source;
        this.destination = destination;
        this.riderId = riderId;

        this.status = RideRequestStatus.REQUESTED;
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------

    public UUID getRequestId() {
        return requestId;
    }

    public UUID getRiderId(){
        return riderId;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public RideRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RideRequestStatus status) {
        this.status = status;
    }
    public Location getSource(){
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    // ----------------------------
    // Object Methods
    // ----------------------------

    @Override
    public String toString() {

        return "RideRequest{" +
                "requestId='" + requestId + '\'' +
                ", rider=" + riderId +
                ", requestTime=" + requestTime +
                ", status=" + status +
                '}';
    }
}