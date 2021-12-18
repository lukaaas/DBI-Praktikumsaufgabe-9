package com.dbi.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DbConfig {

    private String url, user, password;
    private String [] parameter = new String[3];

    protected DbConfig() throws IOException {
        this.loadConfigFromFile();
        this.url = parameter[0];
        this.user = parameter[1];
        this.password = parameter[2];
    }
    // url username und password aus der Datei configFile.propreties einlesen
    private void loadConfigFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/com/dbi/db/configFile.properties"));
        int idx = 0;
        String line;

        while ((line = br.readLine()) != null) {
            this.parameter[idx] = line;
            idx++;
        }
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