package com.feit.feep.dbms.init;

public class InitFactory {

    private InitFactory(){

    }

    public static InitBasicTable getInitBasicTable() {
        return new InitBasicTable();
    }

    public static InitCacheData getInitCacheData() {
        return new InitCacheData();
    }
}
