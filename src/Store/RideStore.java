package Store;

import Models.Ride;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RideStore {

    private final Map<UUID, Ride> activeRides = new HashMap<>();
    private final Map<UUID, Ride> completedRides = new HashMap<>();

    public void addRide(Ride ride) {
        activeRides.put(ride.getRideId(), ride);
    }

    public void moveToCompleted(Ride ride) {
        activeRides.remove(ride.getRideId());
        completedRides.put(ride.getRideId(), ride);
    }
}