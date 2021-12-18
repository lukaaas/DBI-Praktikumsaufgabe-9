package com.dbi.transactions;

import com.dbi.db.DataSource;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class Einzahlung  {

    private final static String stmtUpdate = "UPDATE branches SET balance = balance + ? WHERE branchid = ?; UPDATE tellers SET balance = balance + ? WHERE tellerid = ?; UPDATE accounts SET balance = balance + ? WHERE accid = ?; SELECT balance FROM branches WHERE branchid = ?";
    private final static String stmtInsert ="SET FOREIGN_KEY_CHECKS =0;INSERT INTO history (accid,tellerid,delta,branchid, accbalance, cmmnt) VALUES(?,?,?,?,?,?);";
    private static DataSource hikari;

    static {
        try {
            hikari = new DataSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ausfuehren(int accid, int tellerid, int branchid, int delta) {
        LocalDateTime date = LocalDateTime.now();
        int accbalance = 0;
        String datum = date.toString();
        Kontostand kontostand = new Kontostand();
        try (Connection conn = hikari.getConnection();
             PreparedStatement statementUpdate = conn.prepareStatement(stmtUpdate);
             PreparedStatement statementInsert = conn.prepareStatement(stmtInsert);){

            statementUpdate.setInt(1,delta);
            statementUpdate.setInt(2,branchid);
            statementUpdate.setInt(3,delta);
            statementUpdate.setInt(4,tellerid);
            statementUpdate.setInt(5,delta);
            statementUpdate.setInt(6,accid);
            statementUpdate.setInt(7,branchid);
            statementUpdate.executeUpdate();

            System.out.println("Einzahlung eingeführt");
            accbalance = kontostand.lesen(accid);

            statementInsert.setInt(1,accid);
            statementInsert.setInt(2,tellerid);
            statementInsert.setInt(3,delta);
            statementInsert.setInt(4,branchid);
            statementInsert.setInt(5,accbalance);
            statementInsert.setString(6, " " + date); //30 Caracters
            statementInsert.executeUpdate();
            statementInsert.executeUpdate("SET FOREIGN_KEY_CHECKS =1;");

            //hikari.getHirakiDataSource().close(); // Try() kümmert sich umd das close()
        } catch (Exception e) {e.printStackTrace();}
    }
}