package Concurrency;

import Models.Location;
import Models.RideRequest;
import Models.Rider;
import Store.RideRequestQueue;
import Util.IdGenerator;
import Util.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class RideProducer implements Runnable{
    private String name;
    private boolean generate = true;
    private int delayInSecondsMax;
    private int delayInSecondsMin;
    private final Logger logger  = new Logger(RideProducer.class);

    private final RideRequestQueue rrq;
    public RideProducer(RideRequestQueue rrq){
        this.name = Thread.currentThread().getName();
        this.rrq = rrq;
        this.delayInSecondsMax = 7;
        this.delayInSecondsMin = 3;
    }
    @Override
    public void run() {
        logger.print("Ride Producer Thread Started : " + name);
        this.startGeneratingRide();
    }

    private void startGeneratingRide(){
        while (generate){
            Rider r = createRider();
            RideRequest rr = this.createRideRequest(r);
            this.rrq.addRideRequest(rr);
            try {
                int sleepTIme = ThreadLocalRandom.current().nextInt(delayInSecondsMin*1000,delayInSecondsMax*1000);
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

    private RideRequest createRideRequest(Rider r){
        RideRequest rr = new RideRequest(r);
        return rr;
    }
}
