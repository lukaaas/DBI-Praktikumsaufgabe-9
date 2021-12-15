import java.sql.*;

public class Tables
{
    public static void createTables(Connection conn)throws SQLException
    {
        Statement stmt = conn.createStatement();

        String branches = ("CREATE TABLE branches (branchid int not null AUTO_INCREMENT, branchname char(20) not null,balance int not null, address char(72) not null, primary key(branchid)) ENGINE=MyISAM;");
        String accounts = ("CREATE TABLE accounts(accid int not null AUTO_INCREMENT, name char(20) not null,balance int not null, branchid int not null, address char(68) not null, primary key(accid)) ENGINE=MyISAM;");
        String tellers = ("CREATE TABLE tellers(tellerid int not null AUTO_INCREMENT, tellername char(20) not null,balance int not null, branchid int not null, address char(68)not null, primary key(tellerid)) ENGINE=MyISAM;");
    //    String history = ("CREATE TABLE history(accid int not null AUTO_INCREMENT, tellerid int not null, delta int not null,branchid int not null, accbalance int not null, cmmnt char(30) not null, foreign key(accid) references accounts(accid),foreign key(tellerid) references tellers(tellerid), foreign key (branchid) references branches(branchid)) ENGINE=MyISAM;");
        stmt.execute(branches);
        stmt.execute(accounts);
        stmt.execute(tellers);
    //    stmt.execute(history);

        System.out.println("Tabellen wurden erstellt");
    }
}
