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
    private final RideService rideService;

    public MatchingService(
            DriverService driverService,
            RideService rideService,
            RideStore rideStore) {

        this.driverService = driverService;
        this.rideService = rideService;
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
                driverService.findAndAssignNearestDriver(
                        rideRequest
                                .getSource()
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

        logger.print(
                "Driver Assigned : "
                        + nearestDriver.getName()
        );

        // Create Ride
        Ride ride = rideService.createRide(
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

}