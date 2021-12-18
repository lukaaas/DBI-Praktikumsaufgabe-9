package com.dbi.transactions;

import com.dbi.db.DataSource;
import java.io.IOException;
import java.sql.*;

public class Analyse {

    private final static String query =
            "SELECT accid,tellerid,delta,branchid,accbalance,cmmnt FROM history WHERE delta = ?;";
    private static DataSource hikari;

    static {
        try {
            hikari = new DataSource();
        } catch (IOException e) {e.printStackTrace();}
    }

    public static void ausfuehren(int delta) throws SQLException {

        try (Connection conn = hikari.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);){

            statement.setInt(1,delta);
            ResultSet rs = statement.executeQuery();
            System.out.println();
            if(!rs.next())
                System.out.println("Es gab keine Transactions mit eine einzahlung mit eine höhe von: " + delta);
            else{
                System.out.println("Die Einzahlungen betragen:");
                while (rs.next()) {
                    System.out.print("accid: " + rs.getInt(1)+"\t");
                    System.out.print("tellerid: " + rs.getInt(2)+"\t");
                    System.out.print("delta: " + rs.getInt(3)+"\t");
                    System.out.print("branchid: " + rs.getInt(4)+"\t");
                    System.out.print("accbalance: " + rs.getInt(5)+"\t");
                    System.out.println("cmmnt: " + rs.getString(6));
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
}