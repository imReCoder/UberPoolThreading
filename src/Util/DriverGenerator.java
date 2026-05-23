package Util;

import Models.Driver;
import Models.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DriverGenerator {
    private static final String[] DRIVER_NAMES = {
            "Arun",
            "Vijay",
            "Karthik",
            "Surya",
            "Rahul",
            "Ajay",
            "Manoj",
            "Dinesh",
            "Sathish",
            "Praveen",
            "Ranjit",
            "Vineeth",
            "Dhruv"
    };
    public static List<Driver> generateDrivers(int n){

        List<Driver> drivers = new ArrayList<Driver>();
        for (int i = 0; i < n; i++) {
            Driver driver = generateDriver(i);
            drivers.add(driver);
        }
        return drivers;
    }

    private static Driver generateDriver(int index){
        String name = randomDriverName();
        Location location = Location.randomChennaiLocation();
        return new Driver(name,location);
    }

    private static String randomDriverName() {

        int index =
                ThreadLocalRandom.current()
                        .nextInt(DRIVER_NAMES.length);

        return DRIVER_NAMES[index];
    }
}
