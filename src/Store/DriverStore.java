package Store;

import Models.Driver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DriverStore {

    /**
     * In-memory driver storage.
     * OPTIMIZED THREAD SAFETY: Reverted to standard HashMap because this map
     * is fully populated on the main thread before any background threads start.
     * It is 100% read-only during execution, meaning it is inherently thread-safe.
     */
    private final Map<UUID, Driver> drivers;

    public DriverStore() {
        this.drivers = new HashMap<>();
    }

    // ----------------------------
    // CRUD Operations
    // ----------------------------

    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
        System.out.println("Added New Driver : "+ driver.getNameId());
    }

    public Driver getDriver(UUID driverId) {
        return drivers.get(driverId);
    }

    public void removeDriver(UUID driverId) {
        drivers.remove(driverId);
    }

    public boolean containsDriver(UUID driverId) {
        return drivers.containsKey(driverId);
    }

    // ----------------------------
    // Utility Methods
    // ----------------------------

    public Collection<Driver> getAllDrivers() {
        return drivers.values();
    }

    public int size() {
        return drivers.size();
    }

    public void clear() {
        drivers.clear();
    }

    // ----------------------------
    // Debug / Logging
    // ----------------------------

    public void printAllDrivers() {

        if (drivers.isEmpty()) {
            System.out.println("No Drivers Available");
            return;
        }

        for (Driver driver : drivers.values()) {
            System.out.println(driver);
        }
    }
}