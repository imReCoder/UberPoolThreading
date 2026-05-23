package Models;

import Util.IdGenerator;

import java.util.UUID;

public class Driver {

    private final UUID id;

    private String name;

    private Location location;

    private boolean available;

    public Driver(
            String name,
            Location location) {

        this.id = IdGenerator.generateId();
        this.name = name;
        this.location = location;
        this.available = true;
    }

    // ----------------------------
    // Business Methods
    // ----------------------------

    public void assignDriver() {
        this.available = false;
    }

    public void releaseDriver() {
        this.available = true;
    }

    public void moveRandomly(double maxStep) {
        this.location.moveRandomly(maxStep);
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
        return name + id;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // ----------------------------
    // Object Methods
    // ----------------------------

    @Override
    public String toString() {

        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", available=" + available +
                '}';
    }
}