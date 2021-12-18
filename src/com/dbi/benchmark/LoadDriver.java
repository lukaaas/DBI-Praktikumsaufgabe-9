package com.dbi.benchmark;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class LoadDriver {

    private final static int PERIOD = 50;
    private final static int SETTLING_PHASE_IN_MIN = 4;
    private final static int MEASUREMENT_PHASE_IN_MIN = 5;
    private final static int DECAY_PHASE_IN_MIN = 1;
    private double start;
    private final Timer timer;
    PreparedTransaction transaction = new PreparedTransaction();

    public LoadDriver() throws IOException {
        timer = new Timer();
    }

    public void startLoadDriver() throws InterruptedException {
        timer.schedule(transaction, 0, PERIOD);
        TimeUnit.MINUTES.sleep(4);
        TimeUnit.MINUTES.sleep(5);
        TimeUnit.MINUTES.sleep(1);
        timer.cancel();
    }
}