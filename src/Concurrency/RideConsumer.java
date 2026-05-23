package Concurrency;

import Models.Ride;
import Models.RideRequest;
import Service.MatchingService;
import Store.RideRequestQueue;
import Util.Logger;

public class RideConsumer implements Runnable {
    private final MatchingService matchingService;
    private boolean consume = true;

    private final RideRequestQueue rrq;

    private final Logger logger = new Logger(RideConsumer.class);
    private String name;

    public RideConsumer(RideRequestQueue rrq, MatchingService matchingService){
        this.rrq = rrq;
        this.matchingService = matchingService;
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
                logger.print("Ride Request received "+ rr.getRequestNameId());
                Ride ride = matchingService.matchDriver(rr);
                logger.print("Consumed a Ride Request: "+rr.getRequestNameId());
            } catch (InterruptedException e) {
                logger.print("Interruption in Consuming Ride");
            }
        }
    }
}
