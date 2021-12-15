import java.sql.*;
import java.text.DateFormat;
import java.time.LocalDate;

public class TableValue {
    static void fillBranches(int n, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO branches VALUES(?,'abababababababababab',0,'abcdefghijklmnopqrstuvwxyzababababababababababababababababababababababaa')");

        for (int i = 1; i <= n; i++) {
            stmt.setInt(1, i);
            stmt.addBatch(); //Executes the query
        }
        try {
            stmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Tabelle branches wurde gefuellt");
    }

    static void fillAccounts(int n, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts VALUES(?,'abcdefghijklmnopqrst',0,?,'abcdefghijklmnopqrstuvwxyzababababababababababababababababababababab')");

        for (int i = 1; i <= n * 100000; i++) {
            stmt.setInt(1, i);
            stmt.setInt(2, (int) (Math.random() * n + 1));
            stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        System.out.println("Tabelle accounts wurde gefuellt");
    }

    static void fillTellers(int n, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO tellers VALUES(?,'abcdefghijklmnopqrst',0,?,'abcdefghijklmnopqrstuvwxyzababababababababababababababababababababab')");

        for (int i = 1; i <= n * 10; i++) {
            stmt.setInt(1, i);
            stmt.setInt(2, (int) (Math.random() * n + 1));
            stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        stmt.close();
        System.out.println("Tabelle tellers wurde gefuellt");
    }
/*    static void fillHistory(int n, Connection conn)throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO history VALUES(?,?,0,?,0,'ababababababababababababababab')");

        for(int i = 1; i <= n * 1; i++)
        {

            stmt.setInt(1,(int) (Math.random() * n*100000 + 1));
            stmt.setInt(2,(int) (Math.random() * n*10 + 1));
            stmt.setInt(3,(int) (Math.random() * n + 1));


            stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        stmt.close();
        System.out.println("Tabelle tellers wurde gefuellt");
    }*/

    static void kontostand(int accid, int n, Connection conn) throws SQLException {

        PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM accounts WHERE accid = " + accid);
        ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE accid = " + accid);
        System.out.println("Der Kontostand beträgt:");
        while (rs.next()) {
            System.out.println(rs.getInt(1));
        }
        conn.commit();
        stmt.close();

    }

    static void einzahlung(int accid, int tellerid, int branchid, int delta, Connection conn) {

        LocalDate date = LocalDate.now();
        String datum = date.toString();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT balance FROM branches WHERE branchid = " + branchid);

            stmt.executeUpdate("UPDATE branches SET balance = balance + '" + delta + "' WHERE branchid = " + branchid);
            stmt.executeUpdate("UPDATE tellers SET balance = balance + '" + delta + "' WHERE tellerid = " + tellerid);
            stmt.executeUpdate("UPDATE accounts SET balance = balance + '" + delta + "' WHERE accid = " + accid);

            stmt.executeUpdate("INSERT INTO history SET accid='" + accid + "',tellerid='" + tellerid + "',delta='" + delta + "',branchid='" + branchid + "', accbalance =0, cmmnt = 'Einzahlung vom''" + datum + "'");
            stmt.executeUpdate("UPDATE history SET accbalance = accbalance +'" + delta + "' WHERE accid = " + accid);
            ResultSet rs = stmt.executeQuery("SELECT balance FROM branches WHERE branchid = " + branchid);

            System.out.println("Der neue Kontostand nach der Einzahlung beträgt:");


            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }

            conn.commit();
            stmt.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void analyse(int delta, Connection conn)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement("SELECT accid,tellerid,delta,branchid,accbalance,cmmnt FROM history WHERE delta =  '" + delta + "'");


            rs = stmt.executeQuery("SELECT accid,tellerid,delta,branchid,accbalance,cmmnt FROM history WHERE delta = '" + delta + "'");

            System.out.println("Die Einzahlungen betragen:");
            while (rs.next()) {
                System.out.print(rs.getInt(1)+"\t");
                System.out.print(rs.getInt(2)+"\t");
                System.out.print(rs.getInt(3)+"\t");
                System.out.print(rs.getInt(4)+"\t");
                System.out.print(rs.getInt(5)+"\t");
                System.out.println(rs.getString(6));

            }

            conn.commit();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
