package com.feit.feep.dbms.init;

import org.springframework.context.ApplicationContext;

public class DBInitFactory {

    private DBInitFactory() {

    }

    public static InitBasicTable getInitBasicTable(ApplicationContext ctx) {
        return new InitBasicTable(ctx);
    }

    public static InitCacheData getInitCacheData(ApplicationContext ctx) {
        return new InitCacheData(ctx);
    }
}
