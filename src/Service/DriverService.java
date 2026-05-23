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

    public void generateDrivers(int n){
        List<Driver> drivers = DriverGenerator.generateDrivers(n);

        for(Driver driver:drivers){
            this.addDriver(driver);
        };
    }
}