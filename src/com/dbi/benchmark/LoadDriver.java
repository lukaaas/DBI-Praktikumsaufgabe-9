package com.dbi.benchmark;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class LoadDriver {

    private final static int PERIOD = 50;
    private final static int SETTLING_PHASE_IN_MIN = 4;
    private final static int MEASUREMENT_PHASE_IN_MIN = 5;
    private final static int DECAY_PHASE_IN_MIN = 1;
    private final Timer timer = new Timer();;
    PreparedTransaction transaction1 = new PreparedTransaction();
    PreparedTransaction transaction2 = new PreparedTransaction();
    PreparedTransaction transaction3 = new PreparedTransaction();
    PreparedTransaction transaction4 = new PreparedTransaction();
    PreparedTransaction transaction5 = new PreparedTransaction();

    public LoadDriver()  {}

    public void startLoadDriver() throws InterruptedException {
        timer.schedule(transaction1, 0, PERIOD);
        timer.schedule(transaction2, 0, PERIOD);
        timer.schedule(transaction3, 0, PERIOD);
        timer.schedule(transaction4, 0, PERIOD);
        timer.schedule(transaction5, 0, PERIOD);

        TimeUnit.MINUTES.sleep(4);
        int startTransactionAmount = PreparedTransaction.getAmountTransaction();
        TimeUnit.MINUTES.sleep(5);
        int endTransactionAmount = PreparedTransaction.getAmountTransaction();
        int result = endTransactionAmount - startTransactionAmount;
        System.out.println("Anzahl Tx Thread1: "  + result);
        System.out.println("Durschnitt pro Sekunde: " + (float)(result/300));
        TimeUnit.MINUTES.sleep(1);
        timer.cancel();
    }
}