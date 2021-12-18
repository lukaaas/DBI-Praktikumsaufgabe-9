package Entwurf;

import java.sql.SQLException;
import java.util.Random;
/*
public class TransactionGenerator {

    private int amoutOfTransaction = 0;
    private Database database;

    public TransactionGenerator(Database database) {
        this.database = database;
    }

    public void run() {
        try {
            int random = new Random().nextInt(100);

            if(random < 35)
                runBalanceOf();

            if(random >= 35 && random <= 85);
                runDeposit();

            if(random> 85)
                runAnalyse();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void runAnalyse() {
    }

    private void runBalanceOf()throws SQLException {
        database.balanceOf(randomAccountId());
    }


    private void runDeposit () throws SQLException{
        database.deposit(randomAccountId(),randomTellerId(),randomBranchesId(),randomDelta());
    }

    private int randomDelta() {
        return 1;
    }

    private int randomBranchesId() {
        return 1;
    }

    private int randomTellerId() {
        return 1;
    }
    private int randomAccountId() {
        return 1;
    }

    public void startCounting() {
    }

    public void stopCounting() {
    }
}*/