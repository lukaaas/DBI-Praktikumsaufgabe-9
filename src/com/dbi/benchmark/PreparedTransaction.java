package com.dbi.benchmark;

import com.dbi.transactions.*;
import java.util.Random;
import java.util.TimerTask;

public class PreparedTransaction extends TimerTask {

    private static int amoutPreparedTransaction;
    //private Kontostand kontostand;
    //private Einzahlung einzahlung;
    //private Analyse analyse;
    private static int randomBranchId;
    private static int randomAccountId;
    private static int randomTellerId;
    private static int randomDelta;
    private static int randomTx;

    @Override
    public void run(){
        initTransaction();
        try {
            if(randomTx <= 35)
                //kontostand.lesen(randomAccountId);
                Kontostand.lesen(randomAccountId);
            if(randomTx > 35 && randomTx <= 85)
                //einzahlung.ausfuehren(randomAccountId,randomTellerId,randomBranchId,randomDelta);
                Einzahlung.ausfuehren(randomAccountId,randomTellerId,randomBranchId,randomDelta);
            if(randomTx > 85)
                //analyse.ausfuehren(randomDelta);
                Analyse.ausfuehren(randomDelta);
        } catch (Exception e) {e.printStackTrace();}
    }
    public static void initTransaction(){
        randomBranchId = new Random().nextInt(50);
        randomAccountId = new Random().nextInt(5000000);
        randomTellerId = new Random().nextInt(500);
        randomDelta = new Random().nextInt(2000);
        randomTx = new Random().nextInt(100);
       /* kontostand = new Kontostand();
        einzahlung = new Einzahlung();
        analyse = new Analyse();*/
        amoutPreparedTransaction++;
    }

    public static int getAmountTransaction(){
        return amoutPreparedTransaction;
    }
}