package Entwurf;

import java.io.IOException;
import java.util.Arrays;

public class DriverManager {

    private static LoadDriver[] drivers1;
    private final LoadDriver [] drivers;


    private DriverManager(LoadDriver[] drivers1, LoadDriver... drivers) {
        this.drivers1 = drivers1;
        this.drivers = drivers;}



    public static DriverManager of(int amount) throws IOException {
        LoadDriver[] drivers = new LoadDriver[amount];

        for (int i = 0; i < amount; i++)
            drivers[i] = new LoadDriver(String.valueOf(i));

        return new DriverManager(drivers1, drivers);

    }

    public void stress (int period){
            for (LoadDriver driver : drivers)
                driver.stress(period);
    }
    public void startCounting() {
        for (LoadDriver driver : drivers)
            driver.startCounting();
    }

    public long [] stopCounting(){ return Arrays.stream(drivers).mapToLong(LoadDriver::stopCounting).toArray();}
    public void cancel() {
        for (LoadDriver driver : drivers)
            driver.cancel();
    }
}