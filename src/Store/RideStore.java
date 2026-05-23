package Store;

import Models.Ride;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RideStore {
    Map<UUID,Ride> rideMap = new HashMap<>();
    public void addRide(Ride ride) {
        rideMap.put(ride.getRideId(),ride);
    }
}
