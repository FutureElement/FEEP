package com.feit.feep.dbms.build;

import java.util.List;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.query.Page;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.dbms.entity.query.SortField;
import com.feit.feep.exception.dbms.BuildSqlException;

public class GeneratorSqlBuild {

    private GeneratorSqlBuild() {

    }

    public static final String KEY_COUNT = "count";

    public static String buildQuerySqlWithoutPage(String moduleName,
                                                  List<QueryParameter> queryParameters,
                                                  List<String> fields,
                                                  List<SortField> sortFields) throws BuildSqlException {
        StringBuilder sql = new StringBuilder();
        // TODO 查询module信息
        return sql.toString();
    }

    public static String buildQuerySql(String moduleName,
                                       List<QueryParameter> queryParameters,
                                       List<String> fields,
                                       List<SortField> sortFields,
                                       Page page) throws BuildSqlException {
        StringBuilder sql = new StringBuilder();
        // TODO
        return sql.toString();
    }

    public static String countSqlBuild(String moduleName) {
        StringBuilder sql = new StringBuilder();
        // TODO
        return sql.toString();
    }

    public static String getSqlByKey(String key) {
        try {
            return (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, key);
        } catch (Exception e) {
            return null;
        }
    }

    public static int[] getPageStartAndEnd(int pageIndex, int pageSize) {
        int start = pageSize * (pageIndex - 1);
        int end = start + pageSize - 1;
        return new int[]{start, end};
    }

    public static String convertArrayToSqlString(String[] datas) {
        if (null == datas || datas.length == 0) {
            return "''";
        }
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            buff.append("'");
            buff.append(datas[i]);
            buff.append("'");
            if (i != (datas.length - 1)) {
                buff.append(",");
            }
        }
        return buff.toString();
    }
}
