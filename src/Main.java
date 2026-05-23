    import Concurrency.DriverLocationUpdater;
    import Concurrency.RideConsumer;
    import Concurrency.RideProducer;
    import Models.Driver;
    import Service.DriverService;
    import Service.MatchingService;
    import Service.RideLifeCycleService;
    import Service.RideService;
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

            RideLifeCycleService rideLifeCycleService = new RideLifeCycleService(rs);


            RideRequestQueue rrq = new RideRequestQueue();

            RideService rideService = new RideService(rrq);
            MatchingService matchingService = new MatchingService(driverService,rideService,rs);


            Thread rideProducerThread = new Thread(new RideProducer(rideService),"Thread - " + RideProducer.class.getSimpleName());
            Thread rideConsumerThread = new Thread(new RideConsumer(rrq,matchingService,rideLifeCycleService),"Thread - "+RideConsumer.class.getSimpleName());


            Thread driverLocationUpdaterThread = new Thread(new DriverLocationUpdater(driverService), "Thread - " + DriverLocationUpdater.class.getSimpleName());

            rideProducerThread.start();
            rideConsumerThread.start();
            driverLocationUpdaterThread.start();
        }
    }