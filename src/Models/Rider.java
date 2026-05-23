package Models;

import java.util.UUID;

public class Rider {

    private final UUID id;

    private String name;

    private Location pickupLocation;

    private Location destinationLocation;

    public Rider(
            UUID id,
            String name,
            Location pickupLocation,
            Location destinationLocation) {

        this.id = id;
        this.name = name;
        this.pickupLocation = pickupLocation;
        this.destinationLocation = destinationLocation;
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameId(){
        return name + "-" +id.toString();
    }

    public Location getPickupLocation() {
        return pickupLocation;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public void setDestinationLocation(
            Location destinationLocation) {

        this.destinationLocation = destinationLocation;
    }

    // ----------------------------
    // Object Methods
    // ----------------------------

    @Override
    public String toString() {

        return "Rider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pickupLocation=" + pickupLocation +
                ", destinationLocation=" + destinationLocation +
                '}';
    }
}