package Service;


import Models.Driver;
import Models.Location;
import Models.Ride;
import Models.RideRequest;
import Store.RideRequestQueue;
import Util.Logger;

import java.util.UUID;

public class RideService {

    private final RideRequestQueue requestQueue;
    private final Logger logger = new Logger(RideService.class);

    public RideService(RideRequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    // Create ride request
    public RideRequest requestRide(Location source, Location destination, UUID riderId) {

        RideRequest rideRequest = new RideRequest(source,destination,riderId);

        requestQueue.addRideRequest(rideRequest);

        logger.print("Ride requested: " + rideRequest.getRequestId());

        return rideRequest;
    }

    // simple fetch (no concurrency safety)
//    public Ride getRide(UUID rideId) {
//        return requestQueue.getRequest();
//    }

    private String generateRideId() {
        return UUID.randomUUID().toString();
    }

    public Ride createRide(RideRequest rideRequest, Driver nearestDriver) {
        Ride ride = new Ride(rideRequest.getRiderId(),nearestDriver.getId(),rideRequest.getSource(),rideRequest.getDestination());
        return ride;
    }
}
