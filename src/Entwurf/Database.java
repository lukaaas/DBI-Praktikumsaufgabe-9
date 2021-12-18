/*package Entwurf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    String url;
    String user;
    String password;
    Connection conn;

    public Database(String [] properties) {
        this.url = properties[1];
        this.user = properties[2];
        this.password = properties[3];

    }

    public void deposit(Object randomAccountId, Object randomTellerId, Object randomBranchesId, Object randomDelta) {
    }

    public void balanceOf(Object randomAccountId) {
    }

    public void connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println("Konnte keine Verbindung herstellen!");
            System.exit(0);
        }
        System.out.println("Datenbankverbindung hergestellt!");
        this.conn = conn;
    }
    public void disconnect() throws SQLException {
        this.conn.close();
    }
}
*/