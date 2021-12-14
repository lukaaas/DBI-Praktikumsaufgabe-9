import java.sql.*;

public class TableValue
{
    static void fillBranches(int n, Connection conn) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO branches VALUES(?,'abababababababababab',0,'abcdefghijklmnopqrstuvwxyzababababababababababababababababababababababaa')");

        for (int i = 1; i <= n; i++)
        {
            stmt.setInt(1,i);
            stmt.addBatch(); //Executes the query
        }
        try {
            stmt.executeBatch();
            conn.commit();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Tabelle branches wurde gefuellt");
    }

    static void fillAccounts(int n, Connection conn) throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO accounts VALUES(?,'abcdefghijklmnopqrst',0,?,'abcdefghijklmnopqrstuvwxyzababababababababababababababababababababab')");

        for(int i = 1; i <= n * 100000; i++)
        {
            stmt.setInt(1,i);
            stmt.setInt(2,(int) (Math.random() * n +1));
            stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        System.out.println("Tabelle accounts wurde gefuellt");
    }

    static void fillTellers(int n, Connection conn)throws SQLException
    {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO tellers VALUES(?,'abcdefghijklmnopqrst',0,?,'abcdefghijklmnopqrstuvwxyzababababababababababababababababababababab')");

        for(int i = 1; i <= n * 10; i++)
        {
            stmt.setInt(1,i);
            stmt.setInt(2,(int) (Math.random() * n + 1));
            stmt.addBatch();
        }
        stmt.executeBatch();
        conn.commit();
        stmt.close();
        System.out.println("Tabelle tellers wurde gefuellt");
    }

    static void kontostand(int accid, Connection conn)throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT accbalance FROM history WHERE accid = "+ accid);
        ResultSet rs = stmt.executeQuery("SELECT balance FROM accounts WHERE accid ="+ accid);
        while(rs.next()){
            System.out.println(rs.getInt(1));
        }
        conn.commit();
        stmt.close();
    }
}