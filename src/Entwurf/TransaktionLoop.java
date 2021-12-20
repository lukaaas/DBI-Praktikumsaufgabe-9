/*
package Entwurf;

import java.sql.Connection;
import java.sql.SQLException;

public class TransaktionLoop extends Thread
{
    public int threadNr;
    double TPS=0;
    double totalTransaction;
    public static int transactionPhase=24000;
    public static int measvringPhase = 54000;
    public static int decayPhase = 6000;
    public static long startTime;

    public TransaktionLoop(int threadNr)
    {
        this.threadNr = threadNr;
    }

    public void run (Connection conn)
    {
        try {

        int rowsAccount = TableValue.coutingRows( "account",conn);
        int rowsTellers = TableValue.coutingRows("tellers",conn);
        int rowsBranches = TableValue.coutingRows("branches",conn);
     //   int rowsHistory = TableValue.coutingRows(String history, conn);

        startTime= System.currentTimeMillis();

        while (System.currentTimeMillis()-startTime<transactionPhase)
        {
            int zufall = (int)(Math.random()*100+1);

            if (zufall<15)
            {
                int zufälligeACCID = (int)(Math.random()*rowsAccount+1);
                    TableValue.kontostand(zufälligeACCID,conn);

            }
            if (zufall<50)
            {
                TableValue.analyse((int)(Math.random()*10000+1),conn);
            }

            else
            {
                int zufälligeACCID = (int)(Math.random()*rowsAccount+1);
                int zufälligeTellerID = (int)(Math.random()*rowsTellers+1);
                int zufälligeBranchid = (int)(Math.random()*rowsBranches+1);
                int zufälligeDelta =(int)(Math.random()*10000+1);
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
