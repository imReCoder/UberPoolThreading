import Concurrency.RideConsumer;
import Concurrency.RideProducer;
import Models.Driver;
import Service.DriverService;
import Service.MatchingService;
import Store.DriverStore;
import Store.RideRequestQueue;
import Store.RideStore;
import Util.DriverGenerator;
import Util.Logger;

import java.util.List;

class Main{
    public static void main(String[] args) {
        Logger logger = new Logger(Main.class);
        DriverStore ds = new DriverStore();
        RideStore rs = new RideStore();

        DriverService driverService = new DriverService(ds);
        driverService.generateDrivers(10);


        MatchingService matchingService = new MatchingService(driverService,rs);
        RideRequestQueue rrq = new RideRequestQueue();

        Thread rideProducerThread = new Thread(new RideProducer(rrq),"Thread - " + RideProducer.class.getSimpleName());
        Thread rideConsumerThread = new Thread(new RideConsumer(rrq,matchingService),"Thread - "+RideConsumer.class.getSimpleName());


        rideProducerThread.start();
        rideConsumerThread.start();
    }
}