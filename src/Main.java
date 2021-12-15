

import java.sql.*;
import java.util.Scanner;

public class Main
{
    static Scanner scan = new Scanner(System.in);

    static void deleteTables(Connection conn) throws SQLException
    {
        Statement stmt = conn.createStatement();
      //  stmt.executeUpdate("Drop TABLE IF EXISTS history");
        stmt.executeUpdate("DROP TABLE IF EXISTS accounts");
        stmt.executeUpdate("DROP TABLE IF EXISTS tellers");
        stmt.executeUpdate("DROP TABLE IF EXISTS branches");
        System.out.println("Alle Tabellen wurden geloescht");
    }

    public static void main(String[] args) throws Exception
    {

//Aufbau der Verbindung
        Connection conn = Connect.dbConnection();

//Loeschen der alten Tabellen branches, accounts, tellers und history
        deleteTables(conn);

//Tabellen werden erstellt
        Tables.createTables(conn);

//Eingabe n
        System.out.println("Geben Sie n ein: ");
        int n = scan.nextInt();
// Eingabe der ACCID zum erfragen des Kontostandes
        System.out.println("Geben Sie die ACCID ein: ");
        int nAccid = scan.nextInt();

//Beginn Zeitmessung
        long start = System.currentTimeMillis();
//Methoden zum Tabellenfuellen werden aufgerufen
        TableValue.fillBranches(n, conn);
        TableValue.fillAccounts(n, conn);
        TableValue.fillTellers(n, conn);
      //  TableValue.fillHistory(n,conn);
        int i =0;
        while (i!=3)
        {
            TableValue.kontostand(nAccid, n, conn);
            TableValue.einzahlung(nAccid, 1, 1, 100, conn);
            i++;
        }
        TableValue.analyse(100,conn);
        long ende = System.currentTimeMillis();
        System.out.println("Die Dauer beträgt "+(ende - start));
        conn.close();
    }
}
