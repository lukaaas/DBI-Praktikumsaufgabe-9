package Entwurf;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PerformanceBenchmark {


    public static final int TPS = 100;
    public static final int SECONDS = 1;
    public static final int MINUTES = 10;

    DriverManager manager;
    int coolDownDuration = MINUTES;
    int driverCount = 10;
    int warmUpDuration = 4 * MINUTES;
    int countingDuration = 5* MINUTES;

    public static void main(String[] args) {
        new PerformanceBenchmark().run();
    }

    public void run() {this.benchmark();}



    private void benchmark(){
        try {
            cleanUp();
            initLoadDrives();
            System.out.println("Warming Up");
            manager.stress(50);
            TimeUnit.SECONDS.sleep(warmUpDuration);

            System.out.println("Starting to count.");
            manager.startCounting();
            TimeUnit.SECONDS.sleep(countingDuration);

            System.out.println("Cooling down");
            long[] allTx = manager.stopCounting();
            TimeUnit.SECONDS.sleep(coolDownDuration);

            manager.cancel();


            long totalTx = Arrays.stream(allTx).sum();
            long averageTx = totalTx / driverCount;
            long txPerSecond = totalTx / countingDuration;

            System.out.println("total transactions: " + totalTx);
            System.out.println("Average transactions: " + averageTx);
            System.out.println("Total transactions per second: " + txPerSecond);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cleanUp() {
    }

    private void initLoadDrives() throws IOException {
        manager = DriverManager.of(driverCount);
    }


}
