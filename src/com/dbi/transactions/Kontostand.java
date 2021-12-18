package com.dbi.transactions;

import com.dbi.db.DataSource;
import java.io.IOException;
import java.sql.*;

public class Kontostand {

    private final static String query = "SELECT balance FROM accounts WHERE accid = ?;";
    private static DataSource hikari;

    static {
        try {
            hikari = new DataSource();
        } catch (IOException e) {e.printStackTrace();}
    }

    public int lesen(int accid) {
        int balance = 0;
        try (Connection conn = hikari.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setInt(1, accid);
            ResultSet rs = statement.executeQuery();

            System.out.println("Der Kontostand beträgt:");
            while (rs.next()) {
                balance = rs.getInt(1);
                System.out.println(balance);
            }
        }catch (Exception e) {e.printStackTrace();}
        return balance;
    }
}