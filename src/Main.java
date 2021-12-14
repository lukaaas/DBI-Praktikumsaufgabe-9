

import java.sql.*;
import java.util.Scanner;

public class Main
{
    static Scanner scan = new Scanner(System.in);

    static void deleteTables(Connection conn) throws SQLException
    {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("Drop TABLE IF EXISTS history");
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

//Beginn Zeitmessung
        long start = System.currentTimeMillis();
//Methoden zum Tabellenfuellen werden aufgerufen
        TableValue.fillBranches(n, conn);
        TableValue.fillAccounts(n, conn);
        TableValue.fillTellers(n, conn);
        TableValue.kontostand(7,n, conn);
        long ende = System.currentTimeMillis();
        System.out.println(ende - start);
        conn.close();
    }
}
