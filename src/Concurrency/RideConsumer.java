package Concurrency;

import Models.RideRequest;
import Service.MatchingService;
import Store.RideRequestQueue;
import Util.Logger;

public class RideConsumer implements Runnable {
    private final MatchingService matchingService;
    private boolean consume = true;

    private final RideRequestQueue rrq;

    private final Logger logger = new Logger(RideConsumer.class);

    public RideConsumer(RideRequestQueue rrq, MatchingService matchingService){
        this.rrq = rrq;
        this.matchingService = matchingService;
    }

    @Override
    public void run() {
        this.startRideConsumer();
    }

    private void startRideConsumer(){
        while(consume){
            try {
                logger.print("Trying to consume a Ride Request");
                RideRequest rr = rrq.getRequest();
                matchingService.matchDriver(rr);
                logger.print("Consumed a Ride Request: "+rr.getRequestNameId());
            } catch (InterruptedException e) {
                logger.print("Interruption in Consuming Ride");
            }
        }
    }
}
