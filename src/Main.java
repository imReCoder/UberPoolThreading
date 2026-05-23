import Concurrency.RideProducer;
import Models.Driver;
import Service.DriverService;
import Store.DriverStore;
import Store.RideRequestQueue;
import Util.DriverGenerator;
import Util.Logger;

import java.util.List;

class Main{
    public static void main(String[] args) {
        Logger logger = new Logger(Main.class);
        DriverStore ds = new DriverStore();
        DriverService driverService = new DriverService(ds);
        driverService.generateDrivers(10);

        RideRequestQueue rrq = new RideRequestQueue();
        Thread rideProducerThread = new Thread(new RideProducer(rrq),"Thread - " + RideRequestQueue.class.getSimpleName());



        rideProducerThread.start();
    }
}