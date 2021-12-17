package com.dbi.transactions;

import com.dbi.db.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Analyse {

    private final static String query = "SELECT accid,tellerid,delta,branchid,accbalance,cmmnt FROM history WHERE delta = ?;";
    private static DataSource hikari = new DataSource();

    public static void analyse(int delta) throws SQLException {

        try (PreparedStatement statement = hikari.getConnection().prepareStatement(query);){

            statement.setInt(1,delta);
            ResultSet rs = statement.executeQuery();

            System.out.println("Die Einzahlungen betragen:");
            while (rs.next()) {
                System.out.print("accid: " + rs.getInt(1)+"\t");
                System.out.print("tellerid: " + rs.getInt(2)+"\t");
                System.out.print("delta: " + rs.getInt(3)+"\t");
                System.out.print("branchid: " + rs.getInt(4)+"\t");
                System.out.print("accbalance: " + rs.getInt(5)+"\t");
                System.out.println("cmmnt: " + rs.getString(6));
            }
            //hikari.getHirakiDataSource().close(); // Try() kümmert sich umd das close()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}