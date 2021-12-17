package com.dbi.benchmark;

import com.dbi.db.DataSource;
import com.dbi.transactions.Analyse;
import com.dbi.transactions.Einzahlung;
import com.dbi.transactions.Kontostand;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.util.Random;

public class PreparedTransaction {

    private static int amoutPreparedTransaction;
    /*private Kontostand kontostand;
    private Einzahlung einzahlung;
    private Analyse analyse;*/
    private static int randomBranchId;
    private static int randomAccountId;
    private static int randomTellerId;
    private static int randomDelta;
    private static int randomTx;

    public PreparedTransaction(){
        this.amoutPreparedTransaction++;
        this.randomBranchId = new Random().nextInt(50);
        this.randomAccountId = new Random().nextInt(5000000);
        this.randomTellerId = new Random().nextInt(500);
        this.randomDelta = 1777 ;//new Random().nextInt(2000);
        this.randomTx = 90;//new Random().nextInt(100);
       /*this.kontostand = new Kontostand();
        this.einzahlung = new Einzahlung();
        this.analyse = new Analyse();*/
    }

    public void run(){
        try {
            /*System.out.println("randomBranchId: " + randomBranchId);
            System.out.println("randomAccountId: " + randomAccountId);
            System.out.println("randomTellerId: " + randomTellerId);
            System.out.println("randomDelta: " + randomDelta);
            System.out.println("randomTx: " + randomTx);*/
            if(randomTx < 35)
                //kontostand.kontostand(randomAccountId);
                Kontostand.kontostand(randomAccountId);
            if(randomTx >= 35 && randomTx <= 85)
                //einzahlung.einzahlung(randomAccountId,randomTellerId,randomBranchId,randomDelta);
                Einzahlung.einzahlung(randomAccountId,randomTellerId,randomBranchId,randomDelta);
            if(randomTx > 85)
                //analyse.analyse(randomDelta);
                Analyse.analyse(randomDelta);
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}