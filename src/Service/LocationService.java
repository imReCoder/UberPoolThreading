package Service;

import Models.Location;
import Models.Ride;

public class LocationService {
    public void updateLocation(Ride ride) {

        Location current = ride.getCurrentLocation();
        if (current == null) {
            current = new Location(ride.getSourceLocation().getLatitude(), ride.getSourceLocation().getLongitude());
        }

        // fake movement logic
        current.setLatitude(current.getLatitude() + 0.001);
        current.setLongitude(current.getLongitude() + 0.001);

        ride.setCurrentLocation(current);
    }
}

