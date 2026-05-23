package Concurrency;

import Models.Location;
import Models.RideRequest;
import Models.Rider;
import Service.RideService;
import Store.RideRequestQueue;
import Util.IdGenerator;
import Util.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class RideProducer implements Runnable{
    private final boolean generate = true;
    private final int delayInMilliSecondsMax;
    private final int delayInMilliSecondsMin;
    private final Logger logger  = new Logger(RideProducer.class);

    private final RideService rideService;
    public RideProducer(RideService rideService){
        this.rideService = rideService;
        this.delayInMilliSecondsMax = 150;
        this.delayInMilliSecondsMin = 100;
    }
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        logger.print("Ride Producer Thread Started : " + name);
        this.startGeneratingRide();
    }

    private void startGeneratingRide(){
        while (generate){
            Rider r = createRider();
            rideService.requestRide(r.getPickupLocation(),r.getDestinationLocation(),r.getId());
            try {
                int sleepTIme = ThreadLocalRandom.current().nextInt(delayInMilliSecondsMin, delayInMilliSecondsMax);
                Thread.sleep(sleepTIme);
            } catch (InterruptedException e) {
                logger.print("Ride Generation Interrupted");
            }
        }
    }

    private Rider createRider(){
        Location riderLocation = Location.randomChennaiLocation();
        Location destinationLocation = Location.randomChennaiLocation();
        return new Rider(IdGenerator.generateId(),"XYZ",riderLocation,destinationLocation);
    }

}
