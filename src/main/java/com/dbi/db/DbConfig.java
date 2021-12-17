package com.dbi.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConfig
{
    private String url;
    private String parameter;
    private String user;
    private String password;

    protected DbConfig() {
        this.url = "jdbc:mysql://127.0.0.1:10/dbi"; //?rewriteBatchedStatements=true&allowMultiQueries=true
        this.user = "sam";
        this.password = "password";
    }

    protected String getUrl() {
        return url;
    }

    protected String getUser() {
        return user;
    }

    protected String getPassword() {
        return password;
    }
}