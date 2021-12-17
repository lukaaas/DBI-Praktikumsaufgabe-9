package com.dbi.transactions;

import com.dbi.db.DataSource;

import java.sql.*;

public class Kontostand {

    private final static String query = "SELECT balance FROM accounts WHERE accid = ?;";
    private static DataSource hikari = new DataSource();

    public int lesen(int accid) {
        int balance = 0;
        try (PreparedStatement statement = hikari.getConnection().prepareStatement(query);) {
            statement.setInt(1, accid);
            ResultSet rs = statement.executeQuery();

            System.out.println("Der Kontostand beträgt:");
            while (rs.next()) {
                balance = rs.getInt(1);
                System.out.println(balance);
            }
            //hikari.getHirakiDataSource().close(); // Try() kümmert sich umd das close()
        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }
}