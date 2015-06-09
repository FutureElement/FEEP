package com.feit.feep.dbms.table;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.datasource.Dialect;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
@Repository
public class BasicTableDaoImpl implements IBasicTableDao {

    @Override
    public String createTable(FeepTable feepTable) throws TableException {
        Global.getInstance().logInfo("create Table :" + feepTable.getName());
        String sql = getCreateSQL(feepTable);
        return null;
    }

    private String getCreateSQL(FeepTable feepTable) {
        StringBuilder stringBuilder = new StringBuilder();
        Dialect dialect = Global.getInstance().getFeepConfig().getDBInfo().getDialect();
        switch (dialect) {
            case POSTGRESQL:
                stringBuilder.append("CREATE TABLE ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append("( ");
                List<FeepTableField> tableFields = feepTable.getTableFields();
                if (null != tableFields) {
                    for (FeepTableField tableField : tableFields) {
                        stringBuilder.append(tableField.getName());
                        stringBuilder.append(" ");
                        stringBuilder.append(" character varying(50) ");
                        if (tableField.getNotnull() == 0) {

                        }
                        stringBuilder.append(" character varying(50) ");
                    }
                }
                stringBuilder.append(" CONSTRAINT ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append(" PRIMARY KEY (id) ");
                stringBuilder.append(" ) WITH ( OIDS=FALSE ); ");
                stringBuilder.append(" ALTER TABLE ");
                stringBuilder.append(feepTable.getName());
                stringBuilder.append(" OWNER TO postgres;");
                return null;
            case ORACLE:
                //TODO
                return null;
            case MYSQL:
                //TODO
                return null;
            case SQLSERVER:
                //TODO
                return null;
            default:
                return null;
        }
    }

    @Override
    public FeepTable getTableById(String id) throws TableException {
        return null;
    }

    @Override
    public boolean modifyTable(FeepTable table) throws TableException {
        return false;
    }

    @Override
    public boolean deleteTableById(String id) throws TableException {
        return false;
    }

    @Override
    public List<FeepTable> queryFeepTable(FeepQueryBean feepQueryBean) throws TableException {
        return null;
    }
}
