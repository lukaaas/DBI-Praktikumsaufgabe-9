package com.dbi.benchmark;

public class PrepareThreads {









}


/*
public static void main(String[] args) throws SQLException {
        //Aufbau der Verbindung
        PrepareConnection pr  = new PrepareConnection();
        Connection conn       = pr.dbConnection();
        Kontostand kontostand = new Kontostand();
        Einzahlung einzahlung = new Einzahlung();
        Analyse analyse       = new Analyse();
        //Eingabe der ACCID zum erfragen des Kontostandes
        Scanner scan = new Scanner(System.in);
        System.out.println("Geben Sie die ACCID ein: ");
        int accid = scan.nextInt();
        scan.close();

        //Zeitmessung
        long start = System.currentTimeMillis();

        int i = 0;
        while (i != 3) {
            kontostand.kontostand(accid, conn);
            einzahlung.einzahlung(accid, 1, 1, 100, conn);
            analyse.analyse(100, conn);
            i++;
        }
        long ende = System.currentTimeMillis();
        System.out.println("Die Dauer beträgt " + (ende - start));
        conn.close();
    }
 */