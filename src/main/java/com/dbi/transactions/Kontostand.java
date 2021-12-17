package com.dbi.transactions;

import com.dbi.db.DataSource;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class Kontostand {

    private final static String query = "SELECT balance FROM accounts WHERE accid = ?;";
    private static  DataSource hikari = new DataSource();

    public static int kontostand(int accid) throws SQLException {
        int balance = 0;
        try (PreparedStatement statement = hikari.getConnection().prepareStatement(query);) {
            statement.setInt(1, accid);
            ResultSet rs = statement.executeQuery();

            System.out.println("Der Kontostand beträgt:" + "accid" + accid);
            while (rs.next()) {
                balance = rs.getInt(1);
                System.out.println(balance);
            }
            //hikari.getHirakiDataSource().close();
        }
        return balance;
    }
}