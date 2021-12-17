package com.dbi.db;

public class DbConfig
{
    // Nur innerhable der Klasse abrufbar
    private String url;
    private String parameter;
    private String user;
    private String password;

    // Nur innerhable der Package abrufbar
    protected DbConfig() {
        this.url = "jdbc:mysql://127.0.0.1:10/dbi";
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