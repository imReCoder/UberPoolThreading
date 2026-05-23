package Concurrency;

import Models.Driver;
import Service.DriverService;
import Util.Logger;

public class DriverLocationUpdater implements Runnable {
    private final DriverService driverService;
    private final Logger logger = new Logger(DriverLocationUpdater.class);
    private boolean update = true;

    public DriverLocationUpdater(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void run() {
        logger.print("Driver Location Updater Started");
        while (update) {
            try {
                for (Driver driver : driverService.getAllDrivers()) {
                    if (driver.isAvailable()) {
                        driverService.moveDriverRandomly(driver.getId(), 0.05);
                    }
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.print("Driver Location Updater Interrupted");
            }
        }
    }
}
