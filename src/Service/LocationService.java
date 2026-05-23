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
        Location next = new Location(current.getLatitude() + 0.001, current.getLongitude() + 0.001);

        ride.setCurrentLocation(next);
    }
}

