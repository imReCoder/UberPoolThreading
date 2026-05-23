package Service;

import Models.*;
import Store.RideStore;
import Util.Logger;

import java.time.LocalDateTime;
import java.util.UUID;

public class MatchingService {

    private final DriverService driverService;

    private final RideStore rideStore;

    private final Logger logger =
            new Logger(MatchingService.class);

    public MatchingService(
            DriverService driverService,
            RideStore rideStore) {

        this.driverService = driverService;
        this.rideStore = rideStore;
    }

    // ----------------------------
    // Main Matching Logic
    // ----------------------------

    public Ride matchDriver(
            RideRequest rideRequest) {

        logger.print(
                "Trying to match driver for request : "
                        + rideRequest.getRequestId()
        );

        Driver nearestDriver =
                driverService.findNearestAvailableDriver(
                        rideRequest.getRider()
                                .getPickupLocation()
                );

        if (nearestDriver == null) {

            logger.print(
                    "No available driver found for request : "
                            + rideRequest.getRequestId()
            );

            rideRequest.setStatus(
                    RideRequestStatus.FAILED
            );

            return null;
        }

        // Assign Driver
        nearestDriver.setAvailable(false);

        logger.print(
                "Driver Assigned : "
                        + nearestDriver.getName()
        );

        // Create Ride
        Ride ride = createRide(
                rideRequest,
                nearestDriver
        );

        // Store Active Ride
        rideStore.addRide(ride);

        logger.print(
                "Ride Created : "
                        + ride.getRideId()
        );

        return ride;
    }

    // ----------------------------
    // Helper Methods
    // ----------------------------

    private Ride createRide(
            RideRequest rideRequest,
            Driver driver) {

        return new Ride(
                UUID.randomUUID(),
                driver,
                rideRequest.getRider(),
                RideStatus.STARTED,
                LocalDateTime.now()
        );
    }
}