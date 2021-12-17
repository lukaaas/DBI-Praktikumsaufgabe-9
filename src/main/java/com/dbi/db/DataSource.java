package com.dbi.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

    private DbConfig config = new DbConfig();
    private HikariConfig hconfig = new HikariConfig();
    private HikariDataSource ds;

    public DataSource() {
        //hconfig.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        hconfig.setJdbcUrl( config.getUrl());
        hconfig.setUsername( config.getUser() );
        hconfig.setPassword( config.getPassword());
        hconfig.addDataSourceProperty( "rewriteBatchedStatements",true );
        hconfig.addDataSourceProperty( "allowMultiQueries" , true );
        ///hconfig.addDataSourceProperty( "useConfigs" , "maxPerformance" );
        hconfig.addDataSourceProperty( "cachePrepStmts" , true );
        hconfig.setAutoCommit(true); // Atomarität
        //hconfig.addDataSourceProperty( "prepStmtCacheSize" , 250 );
        //hconfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , 2048 );
        hconfig.setMaximumPoolSize(10000000);
        ds = new HikariDataSource( hconfig );
    }

    public Connection getConnection() throws SQLException { // Isolation
        Connection connection = ds.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return connection;
    }
    public void closeConnection() throws SQLException {
        ds.close();
    }
    public HikariDataSource getHirakiDataSource(){
        return ds;
    }
}

/*
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Konnte keine Verbindung herstellen!");
            System.exit(0);
        }
        System.out.println("Datenbankverbindung hergestellt!");
        return conn;
 */