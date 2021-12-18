package com.dbi.db;

import java.io.IOException;
import java.sql.*;

public class Table
{
    private DataSource hikari;

    public Table() throws IOException {
        this.hikari = new DataSource();
    }

    public void cleanHistory() {
        try (Connection conn = hikari.getConnection();
             Statement stmt = conn.createStatement();){
             stmt.executeUpdate("DELETE FROM History WHERE 1=1");
             System.out.println("Die Tabelle History ist leer");
        } catch (Exception e) {e.printStackTrace();}
    }
    public void cleanAllTransactions() {
        try (Connection conn = hikari.getConnection();
             Statement stmt = conn.createStatement();){

            stmt.executeUpdate("UPDATE accounts SET balance = 0 WHERE balance <> 1; UPDATE branches SET balance = 0 WHERE balance <> 1; UPDATE tellers SET balance = 0 WHERE balance <> 1;");

            System.out.println("Balance ist zurückgesetzt");
        } catch (Exception e) {e.printStackTrace();}
    }

    public void createTables() {
        try (Connection conn = hikari.getConnection();
             Statement stmt = conn.createStatement();){
            String branches = ("CREATE TABLE branches (branchid int not null AUTO_INCREMENT, branchname char(20) not null,balance int not null, address char(72) not null, primary key(branchid)) ENGINE=MyISAM;");
            String accounts = ("CREATE TABLE accounts(accid int not null AUTO_INCREMENT, name char(20) not null,balance int not null, branchid int not null, address char(68) not null, primary key(accid)) ENGINE=MyISAM;");
            String tellers = ("CREATE TABLE tellers(tellerid int not null AUTO_INCREMENT, tellername char(20) not null,balance int not null, branchid int not null, address char(68)not null, primary key(tellerid)) ENGINE=MyISAM;");
            String history = ("CREATE TABLE history(accid int not null AUTO_INCREMENT, tellerid int not null, delta int not null,branchid int not null, accbalance int not null, cmmnt char(30) not null, foreign key(accid) references accounts(accid),foreign key(tellerid) references tellers(tellerid), foreign key (branchid) references branches(branchid)) ENGINE=MyISAM;");
            stmt.execute(branches);
            stmt.execute(accounts);
            stmt.execute(tellers);
            stmt.execute(history);
            System.out.println("Tabellen wurden erstellt");
        } catch (Exception e) {e.printStackTrace();}
    }

    public void deleteTables()  {
        try (Connection conn = hikari.getConnection();
             Statement stmt = conn.createStatement();){
        //  stmt.executeUpdate("Drop TABLE IF EXISTS history");
        stmt.executeUpdate("DROP TABLE IF EXISTS accounts");
        stmt.executeUpdate("DROP TABLE IF EXISTS tellers");
        stmt.executeUpdate("DROP TABLE IF EXISTS branches;");
        System.out.println("Alle Tabellen wurden geloescht");
        } catch (Exception e) {e.printStackTrace();}
    }
}