package com.dbi.benchmark;

import com.dbi.db.Table;

public class Main {

    public static void main(String[] args) throws Exception {
        Table manageTable = new Table();
        manageTable.cleanHistory();
        LoadDriver ld = new LoadDriver();
        ld.startLoadDriver();
    }
}