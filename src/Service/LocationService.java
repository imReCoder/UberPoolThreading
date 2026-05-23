package Service;

import Models.Location;
import Models.Ride;

public class LocationService {
    public void updateLocation(Ride ride) {

        Location current = ride.getSourceLocation();

        // fake movement logic
        current.setLat(current.getLat() + 0.001);
        current.setLng(current.getLng() + 0.001);

        ride.setCurrentLocation(current);
    }
}

