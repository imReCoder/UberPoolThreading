package Store;

import Models.Ride;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RideStore {

    /**
     * BASIC THREAD SAFETY: We use Collections.synchronizedMap.
     * ALTERNATIVE WAY (Optimization): Use ConcurrentHashMap for higher concurrency throughput.
     */
    private final Map<UUID, Ride> activeRides = Collections.synchronizedMap(new HashMap<>());
    private final Map<UUID, Ride> completedRides = Collections.synchronizedMap(new HashMap<>());

    public void addRide(Ride ride) {
        activeRides.put(ride.getRideId(), ride);
    }

    public void moveToCompleted(Ride ride) {
        activeRides.remove(ride.getRideId());
        completedRides.put(ride.getRideId(), ride);
    }
}