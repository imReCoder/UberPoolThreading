package Models;

import Util.IdGenerator;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class Driver {

    private final UUID id;

    private String name;

    /**
     * OPTIMIZED THREAD SAFETY: We use 'volatile' here because Location is now immutable.
     * When the LocationUpdater thread assigns a new Location object to this reference,
     * the 'volatile' keyword ensures all RideConsumer threads instantly see the updated 
     * memory reference without needing synchronized blocks.
     */
    private volatile Location location;

    /**
     * OPTIMIZED THREAD SAFETY: We use AtomicBoolean instead of a regular boolean.
     * This allows us to perform lock-free "Check-Then-Act" operations (like tryAssign)
     * using CPU-level atomic instructions, completely avoiding slow synchronized blocks.
     */
    private final AtomicBoolean available;

    public Driver(
            String name,
            Location location) {

        this.id = IdGenerator.generateId();
        this.name = name;
        this.location = location;
        this.available = new AtomicBoolean(true);
    }

    // ----------------------------
    // Business Methods
    // ----------------------------

    /**
     * Attempts to atomically assign this driver. 
     * compareAndSet checks if the value is 'true', and if so, changes it to 'false'
     * in a single, atomic CPU operation. Returns true if successful.
     */
    public boolean tryAssign() {
        return this.available.compareAndSet(true, false);
    }

    public void releaseDriver() {
        this.available.set(true);
    }

    public void moveRandomly(double maxStep) {
        this.location = this.location.moveRandomly(maxStep);
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
        return available.get();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
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