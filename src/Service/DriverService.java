package Service;

import Models.Driver;
import Models.Location;
import Store.DriverStore;
import Util.DriverGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DriverService {

    private final DriverStore driverStore;

    public DriverService(DriverStore driverStore) {
        this.driverStore = driverStore;
    }

    // ----------------------------
    // Driver Operations
    // ----------------------------

    public void addDriver(Driver driver) {
        driverStore.addDriver(driver);
    }

    public Driver getDriver(UUID driverId) {
        return driverStore.getDriver(driverId);
    }

    public List<Driver> getAllDrivers() {

        return new ArrayList<>(
                driverStore.getAllDrivers()
        );
    }

    // ----------------------------
    // Availability
    // ----------------------------

    public List<Driver> getAvailableDrivers() {

        List<Driver> availableDrivers =
                new ArrayList<>();

        for (Driver driver : driverStore.getAllDrivers()) {

            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }

        return availableDrivers;
    }

    // ----------------------------
    // Location Operations
    // ----------------------------

    public void updateDriverLocation(
            UUID driverId,
            Location newLocation) {

        Driver driver =
                driverStore.getDriver(driverId);

        if (driver == null) {
            return;
        }

        driver.setLocation(newLocation);
    }

    public void moveDriverRandomly(
            UUID driverId,
            double maxStep) {

        Driver driver =
                driverStore.getDriver(driverId);

        if (driver == null) {
            return;
        }

        driver.moveRandomly(maxStep);
    }

    // ----------------------------
    // Driver Assignment
    // ----------------------------

    public void assignDriver(UUID driverId) {

        Driver driver =
                driverStore.getDriver(driverId);

        if (driver == null) {
            return;
        }

        driver.setAvailable(false);
    }

    public void releaseDriver(UUID driverId) {

        Driver driver =
                driverStore.getDriver(driverId);

        if (driver == null) {
            return;
        }

        driver.setAvailable(true);
    }

    // ----------------------------
    // Nearest Driver Logic
    // ----------------------------

    public Driver findNearestAvailableDriver(
            Location pickupLocation) {

        Driver nearestDriver = null;

        double shortestDistance =
                Double.MAX_VALUE;

        for (Driver driver : getAvailableDrivers()) {

            double distance =
                    driver.getLocation()
                            .distanceTo(pickupLocation);

            if (distance < shortestDistance) {

                shortestDistance = distance;

                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }

    // ----------------------------
    // Thread-Safe Assignment Logic
    // ----------------------------

    /**
     * OPTIMIZED THREAD SAFETY: Lock-Free Optimistic Assignment.
     * We repeatedly find the nearest available driver and try to assign them using
     * a lock-free atomic compareAndSet operation. If another thread assigns them first,
     * the compareAndSet fails and we retry instantly.
     */
    public Driver findAndAssignNearestDriver(Location pickupLocation) {
        while (true) {
            Driver nearestDriver = findNearestAvailableDriver(pickupLocation);

            if (nearestDriver == null) {
                return null; // No available drivers left
            }

            // Attempt atomic assignment
            if (nearestDriver.tryAssign()) {
                return nearestDriver;
            }
            // If tryAssign returns false, another thread took the driver! Loop and try again.
        }
    }

    public void generateDrivers(int n){
        List<Driver> drivers = DriverGenerator.generateDrivers(n);

        for(Driver driver:drivers){
            this.addDriver(driver);
        };
    }
}