package com.dbi.benchmark;

import com.dbi.transactions.*;
import java.util.Random;
import java.util.TimerTask;

public class PreparedTransaction extends TimerTask {

    private static int amoutPreparedTransaction;
    private int id;
    private Kontostand kontostand;
    private Einzahlung einzahlung;
    private Analyse analyse;
    private int randomBranchId;
    private int randomAccountId;
    private int randomTellerId;
    private int randomDelta;
    private int randomTx;

    public PreparedTransaction(){
    }

    @Override
    public void run(){
        initTransaction();
        try {
            if(randomTx <= 35)
                kontostand.lesen(randomAccountId);
                //Kontostand.kontostand(randomAccountId);
            if(randomTx > 35 && randomTx <= 85)
                einzahlung.ausfuehren(randomAccountId,randomTellerId,randomBranchId,randomDelta);
                //Einzahlung.einzahlung(randomAccountId,randomTellerId,randomBranchId,randomDelta);
            if(randomTx > 85)
                analyse.ausfuehren(randomDelta);
                //Analyse.analyse(randomDelta);
        } catch (Exception e) {e.printStackTrace();}
    }
    public void initTransaction(){
        this.randomBranchId = new Random().nextInt(50);
        this.randomAccountId = new Random().nextInt(5000000);
        this.randomTellerId = new Random().nextInt(500);
        this.randomDelta = new Random().nextInt(2000);
        this.randomTx = new Random().nextInt(100);
        this.kontostand = new Kontostand();
        this.einzahlung = new Einzahlung();
        this.analyse = new Analyse();
        this.amoutPreparedTransaction++;
    }

    public static int getAmountTransaction(){
        return amoutPreparedTransaction;
    }
}