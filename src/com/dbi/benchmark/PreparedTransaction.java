package com.dbi.benchmark;

import com.dbi.transactions.Einzahlung;
import com.dbi.transactions.Analyse;
import com.dbi.transactions.Kontostand;

import java.sql.SQLException;
import java.util.Random;

public class PreparedTransaction {

    private int amoutPreparedTransaction;
    private Kontostand kontostand;
    private Einzahlung einzahlung;
    private Analyse analyse;
    private int randomBranchId;
    private int randomAccountId;
    private int randomTellerId;
    private int randomDelta;
    private int randomTx;

    public PreparedTransaction(){
        this.amoutPreparedTransaction++;
        this.randomBranchId = new Random().nextInt(50);
        this.randomAccountId = 3726588;//new Random().nextInt(5000000);
        this.randomTellerId = new Random().nextInt(500);
        this.randomDelta = new Random().nextInt(2000);
        this.randomTx = 12;//new Random().nextInt(100);
       this.kontostand = new Kontostand();
        this.einzahlung = new Einzahlung();
        this.analyse = new Analyse();
    }

    public void run(){
        try {

            if(randomTx < 35)
                kontostand.lesen(randomAccountId);
                //Kontostand.kontostand(randomAccountId);
            if(randomTx >= 35 && randomTx <= 85)
                einzahlung.ausfuehren(randomAccountId,randomTellerId,randomBranchId,randomDelta);
                //Einzahlung.einzahlung(randomAccountId,randomTellerId,randomBranchId,randomDelta);
            if(randomTx > 85)
                analyse.ausfuehren(randomDelta);
                //Analyse.analyse(randomDelta);
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}

           /*System.out.println("randomBranchId: " + randomBranchId);
            System.out.println("randomAccountId: " + randomAccountId);
            System.out.println("randomTellerId: " + randomTellerId);
            System.out.println("randomDelta: " + randomDelta);
            System.out.println("randomTx: " + randomTx);*/