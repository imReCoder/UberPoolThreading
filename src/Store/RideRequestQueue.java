package Store;

import Models.RideRequest;
import Util.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RideRequestQueue {
    private final BlockingQueue<RideRequest> queue = new ArrayBlockingQueue<>(10);
    private Logger logger;

    public RideRequestQueue(){
        this.logger = new Logger(RideRequestQueue.class);
    }

    public void addRideRequest(RideRequest rr){
        try{
        logger.print("Trying to add new ride request : "+ rr.getRequestId());
        queue.put(rr);
        logger.print("Ride Request Added successfully : "+ rr.getRequestId());
        } catch (InterruptedException e) {
            logger.print("Add Ride Request Interrupted");
        }

    }

    public  RideRequest getRequest() throws InterruptedException {
        return queue.take();
    }
}
