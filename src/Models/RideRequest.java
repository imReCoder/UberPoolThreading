package Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class RideRequest {

    private final UUID requestId;

    private final Rider rider;

    private final LocalDateTime requestTime;

    private RideRequestStatus status;

    public RideRequest(Rider rider) {

        this.requestId = UUID.randomUUID();

        this.rider = rider;

        this.requestTime = LocalDateTime.now();

        this.status = RideRequestStatus.REQUESTED;
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------

    public UUID getRequestId() {
        return requestId;
    }

    public String getRequestNameId(){
        return rider.getName() + "-" + requestId;
    }

    public Rider getRider() {
        return rider;
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

    // ----------------------------
    // Object Methods
    // ----------------------------

    @Override
    public String toString() {

        return "RideRequest{" +
                "requestId='" + requestId + '\'' +
                ", rider=" + rider +
                ", requestTime=" + requestTime +
                ", status=" + status +
                '}';
    }
}