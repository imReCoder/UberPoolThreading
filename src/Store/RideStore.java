package Store;

import Models.Ride;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.UUID;

public class RideStore {

    /**
     * OPTIMIZED THREAD SAFETY: Upgraded to ConcurrentHashMap.
     * Unlike synchronizedMap (which locks the entire map), ConcurrentHashMap uses
     * bucket-level locking, allowing multiple consumers to add/move rides simultaneously.
     */
    private final Map<UUID, Ride> activeRides = new ConcurrentHashMap<>();
    private final Map<UUID, Ride> completedRides = new ConcurrentHashMap<>();

    public void addRide(Ride ride) {
        activeRides.put(ride.getRideId(), ride);
    }

    public void moveToCompleted(Ride ride) {
        activeRides.remove(ride.getRideId());
        completedRides.put(ride.getRideId(), ride);
    }
}