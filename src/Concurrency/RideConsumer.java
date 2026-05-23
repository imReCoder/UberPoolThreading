package Concurrency;

import Models.Ride;
import Models.RideRequest;
import Service.MatchingService;
import Service.RideLifeCycleService;
import Store.RideRequestQueue;
import Util.Logger;

public class RideConsumer implements Runnable {
    private final MatchingService matchingService;
    private final RideLifeCycleService rideLifeCycleService;
    private boolean consume = true;

    private final RideRequestQueue rrq;

    private final Logger logger = new Logger(RideConsumer.class);
    private String name;

    public RideConsumer(RideRequestQueue rrq, MatchingService matchingService, RideLifeCycleService rideLifeCycleService){
        this.rrq = rrq;
        this.matchingService = matchingService;
        this.rideLifeCycleService = rideLifeCycleService;
    }

    @Override
    public void run() {
        this.name = Thread.currentThread().getName();
        logger.print("Ride Consumer Thread Started: "+ name);
        this.startRideConsumer();
    }

    private void startRideConsumer(){
        while(consume){
            try {
                logger.print("Waiting to consume a Ride Request");
                RideRequest rr = rrq.getRequest();
                logger.print("Ride Request received "+ rr.getRequestId());
                Ride ride = matchingService.matchDriver(rr);
                if (ride != null) {
                    rideLifeCycleService.startRide(ride);
                    logger.print("Consumed a Ride Request: "+rr.getRequestId());
                } else {
                    logger.print("No driver matched for Request: "+rr.getRequestId());
                }
            } catch (InterruptedException e) {
                logger.print("Interruption in Consuming Ride");
            }
        }
    }
}
