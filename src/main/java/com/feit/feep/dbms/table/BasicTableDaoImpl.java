package com.feit.feep.dbms.table;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.TableSqlBuild;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
@Repository
public class BasicTableDaoImpl implements IBasicTableDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createTable(FeepTable feepTable) throws TableException {
        Global.getInstance().logInfo("create Table :" + feepTable.getName());
        try {
            String sql = getCreateSQL(feepTable);
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    private String getCreateSQL(FeepTable feepTable) {
        TableSqlBuild tableSqlBuild = new TableSqlBuild();
        return tableSqlBuild.getCreateSQL(feepTable);
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
