package Service;

import Models.Ride;
import Models.RideStatus;
import Store.RideStore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RideLifeCycleService {

    private final RideStore rideStore;
    private final ExecutorService executor;
    private final LocationService locationService = new LocationService();
    private final DriverService driverService;

    public RideLifeCycleService(RideStore rideStore, DriverService driverService) {
        this.rideStore = rideStore;
        this.driverService = driverService;
        this.executor = Executors.newFixedThreadPool(10);
    }

    // ENTRY POINT AFTER MATCHING
    public void startRide(Ride ride) {

        ride.setStatus(RideStatus.IN_PROGRESS);

        rideStore.addRide(ride);

        executor.submit(() -> executeRide(ride));
    }

    private void executeRide(Ride ride) {

        try {

            for (int i = 0; i < 10; i++) {

                Thread.sleep(1000); // simulate travel

                locationService.updateLocation(ride);

                System.out.println(
                        "Ride " + ride.getRideId() +
                                " moving... step " + i + " Location: " + ride.getCurrentLocation()
                );
            }

            completeRide(ride);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void completeRide(Ride ride) {

        ride.setStatus(RideStatus.COMPLETED);

        rideStore.moveToCompleted(ride);

        driverService.releaseDriver(ride.getDriverId());

        System.out.println("Ride completed: " + ride.getRideId() + " and driver released.");
    }
}