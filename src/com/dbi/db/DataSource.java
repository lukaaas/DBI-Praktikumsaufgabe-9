package com.dbi.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSource {

    private DbConfig config = new DbConfig();
    private HikariConfig hconfig = new HikariConfig();
    private HikariDataSource dataSource;

    public DataSource() throws IOException {

        hconfig.setJdbcUrl( config.getUrl());
        hconfig.setUsername( config.getUser() );
        hconfig.setPassword( config.getPassword());
        hconfig.addDataSourceProperty( "rewriteBatchedStatements",true );
        hconfig.addDataSourceProperty( "allowMultiQueries" , true );
        hconfig.setAutoCommit(false); // Atomarität
        dataSource = new HikariDataSource( hconfig );
        hconfig.setMaximumPoolSize(1000);

        //hconfig.addDataSourceProperty( "useConfigs" , "maxPerformance" );
        //hconfig.addDataSourceProperty( "cachePrepStmts" , true );
        //hconfig.addDataSourceProperty( "prepStmtCacheSize" , 250 );
        //hconfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , 2048 );

    }

    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);// Isolation garantieren
        return connection;
    }
    public void closeConnection() throws SQLException {dataSource.close();}
    public HikariDataSource getHirakiDataSource(){
        return dataSource;
    }
}