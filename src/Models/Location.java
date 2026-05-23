package Models;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------
    // BASIC THREAD SAFETY: We synchronize access methods to prevent stale reads.
    // ALTERNATIVE WAY (Optimization): Make Location objects immutable and update references via AtomicReference.

    public synchronized double getLatitude() {
        return latitude;
    }

    public synchronized void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public synchronized double getLongitude() {
        return longitude;
    }

    public synchronized void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // ----------------------------
    // Distance Calculation
    // ----------------------------

    /**
     * Simple Euclidean distance.
     * Good enough for simulation.
     */
    public synchronized double distanceTo(Location other) {

        double dx = this.latitude - other.latitude;
        double dy = this.longitude - other.longitude;

        return Math.sqrt(dx * dx + dy * dy);
    }

    // ----------------------------
    // Random Movement Simulation
    // ----------------------------

    /**
     * Simulates driver movement.
     */
    public synchronized void moveRandomly(double maxStep) {

        double latOffset =
                ThreadLocalRandom.current()
                        .nextDouble(-maxStep, maxStep);

        double lonOffset =
                ThreadLocalRandom.current()
                        .nextDouble(-maxStep, maxStep);

        this.latitude += latOffset;
        this.longitude += lonOffset;
    }

    // ----------------------------
    // Static Factory Methods
    // ----------------------------

    /**
     * Creates random location within range.
     */
    public static Location randomLocation(
            double minLat,
            double maxLat,
            double minLon,
            double maxLon) {

        double lat =
                ThreadLocalRandom.current()
                        .nextDouble(minLat, maxLat);

        double lon =
                ThreadLocalRandom.current()
                        .nextDouble(minLon, maxLon);

        return new Location(lat, lon);
    }

    /**
     * Generates random Chennai-like coordinates.
     */
    public static Location randomChennaiLocation() {

        return randomLocation(
                12.90,
                13.15,
                80.10,
                80.35
        );
    }

    // ----------------------------
    // Object Methods
    // ----------------------------

    @Override
    public String toString() {
        return String.format(
                "(%.5f, %.5f)",
                latitude,
                longitude
        );
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Location)) {
            return false;
        }

        Location location = (Location) o;

        return Double.compare(location.latitude, latitude) == 0
                && Double.compare(location.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}