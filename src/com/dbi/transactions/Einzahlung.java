package com.dbi.transactions;

import com.dbi.db.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static void ausfuehren(int accid, int tellerid, int branchid, int delta) {
        LocalDateTime date = LocalDateTime.now();
        int accbalance = 0;
        String datum = date.toString();
        try (Connection conn = hikari.getConnection();
             PreparedStatement statementUpdate = conn.prepareCall("einzahlung(" + accid
                     + ", "+ tellerid +", " + branchid+", " + delta+")");
             PreparedStatement statementInsert = conn.prepareStatement(stmtInsert);){

//            statementUpdate.setInt(1,delta);
  //          statementUpdate.setInt(2,branchid);
   //         statementUpdate.setInt(3,delta);
     //       statementUpdate.setInt(4,tellerid);
     //       statementUpdate.setInt(5,delta);
     //       statementUpdate.setInt(6,accid);
     //       statementUpdate.setInt(7,branchid);
      //      statementUpdate.executeUpdate();

            ResultSet rs = statementInsert.executeQuery("SELECT balance FROM branches WHERE branchId =" + branchid);
            while(rs.next()){
                accbalance = rs.getInt(1);
            }

         //   System.out.println("Der Kontostand nach der Einzahlung beträgt:\n" + accbalance);

            statementInsert.setInt(1,accid);
            statementInsert.setInt(2,tellerid);
            statementInsert.setInt(3,delta);
            statementInsert.setInt(4,branchid);
            statementInsert.setInt(5,accbalance);
            statementInsert.setString(6, " " + date); //30 Caracters
            statementInsert.executeUpdate();
            statementInsert.executeUpdate("SET FOREIGN_KEY_CHECKS =1;");

        } catch (Exception e) {e.printStackTrace();}
    }

    /*
     public static void einzahlung(int accid, int tellerid, int branchid, int delta) throws SQLException {


        LocalDate date = LocalDate.now();
        String datum = date.toString();

        PreparedStatement stmt = null;
        try (Connection conn = hikari.getConnection();){
            stmt = conn.prepareStatement("SELECT balance FROM branches WHERE branchid = " + branchid);

            stmt.executeUpdate("UPDATE branches SET balance = balance + '" + delta + "' WHERE branchid = " + branchid);
            stmt.executeUpdate("UPDATE tellers SET balance = balance + '" + delta + "' WHERE tellerid = " + tellerid);
            stmt.executeUpdate("UPDATE accounts SET balance = balance + '" + delta + "' WHERE accid = " + accid);

            stmt.executeUpdate("INSERT INTO history SET accid='" + accid + "',tellerid='" + tellerid + "',delta='" + delta + "',branchid='" + branchid + "', accbalance =0, cmmnt = 'Einzahlung vom'" + datum + "'");
            stmt.executeUpdate("UPDATE history SET accbalance = accbalance +'" + delta + "' WHERE accid = " + accid);
            ResultSet rs = stmt.executeQuery("SELECT balance FROM branches WHERE branchid = " + branchid);

            System.out.println("Der neue Kontostand nach der Einzahlung beträgt:");


            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }

            //conn.commit();
            stmt.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     */
}