/*
package Entwurf;


import java.io.IOException;
import java.util.Timer;

public class LoadDriver {
    private final String id;
    private final Database database;
    private final Timer timer;
    private TransactionGenerator transaction;

    public LoadDriver(String id) throws IOException {
        this.id = id;
        this.database = new Database(SetupBenchmark.getProperties("app.properties"));
        timer = new Timer();
        transaction = new TransactionGenerator(database);
    }

    public void stress (int period){
        try {
            this.database.connect();
            //timer.scheduleAtFixedRate(transaction,0,period);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancel(){
        this.timer.cancel();
        try {
            this.database.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void startCounting() {
        this.transaction.startCounting();
    }

    public long stopCounting() {
        return 1;
    }
}
*/