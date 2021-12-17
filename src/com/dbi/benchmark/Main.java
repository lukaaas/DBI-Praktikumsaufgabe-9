package com.dbi.benchmark;

import com.dbi.db.Table;

public class Main {

    public static void main(String[] args) throws Exception {
        Table manageTable = new Table();
        manageTable.cleanHistory();
        manageTable.closeConnection();
        int i = 0;

        while (i < 6){
            PreparedTransaction pt = new PreparedTransaction();
            pt.run();
            i++;
        }
    }
}