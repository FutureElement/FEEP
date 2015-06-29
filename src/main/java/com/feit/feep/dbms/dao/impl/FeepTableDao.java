package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.dao.IFeepTableDao;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.FeepSQL;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 数据表dao实现类
 * Created by ZhangGang on 2015/6/9 0009.
 */
@Repository
public class FeepTableDao implements IFeepTableDao {

    private static final String TABLENAME = "feep_table";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String KEY_GETTABLEBYID = "sql.dbms.table.getTableById";
    private static final String KEY_DELETETABLEBYID = "sql.dbms.table.deleteTableById";
    private static final String KEY_MODIFYTABLE = "sql.dbms.table.modifyTableInfo";
    private static final String KEY_COUNTTABLE = "sql.dbms.table.countTable";
    private static final String KEY_INSERT = "sql.dbms.table.insert";

    @Override
    public void createTable(FeepTable feepTable, List<FeepTableField> tableFields) throws TableException {
        Global.getInstance().logInfo("create Table :" + feepTable.getName());
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            jdbcTemplate.execute(basicSqlBuild.getCreateSQL(feepTable, tableFields));
        } catch (Exception e) {
            throw new TableException("create table: " + feepTable.getName() + "error", e);
        }
    }

    @Override
    public String insertFeepTable(FeepTable feepTable) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_INSERT);
            if (FeepUtil.isNull(feepTable.getId())) {
                feepTable.setId(FeepUtil.getUUID());
            }
            jdbcTemplate.update(sql, convertFeepTableToParameterForInsert(feepTable));
            return feepTable.getId();
        } catch (Exception e) {
            throw new TableException("insertFeepTable [" + feepTable.getName() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertFeepTableToParameterForInsert(FeepTable feepTable) {
        return new Object[]{feepTable.getId(), feepTable.getName(), feepTable.getShowname(), feepTable.getTabletype(), feepTable.getDescription(), feepTable.getDatasourceid()};
    }

    private Object[] convertFeepTableToParameterForUpdate(FeepTable feepTable) {
        return new Object[]{feepTable.getName(), feepTable.getShowname(), feepTable.getTabletype(), feepTable.getDescription(), feepTable.getDatasourceid(), feepTable.getId()};
    }

    @Override
    public FeepTable getTableById(String id) throws TableException {
        FeepTable feepTable = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_GETTABLEBYID);
            List<FeepTable> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getMapper(FeepTable.class));
            if (null != result) {
                feepTable = result.get(0);
            }
            return feepTable;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean modifyTableInfo(FeepTable table) throws TableException {
        try {
            int i = 0;
            if (null != table && !FeepUtil.isNull(table.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_MODIFYTABLE);
                i = jdbcTemplate.update(sql, convertFeepTableToParameterForUpdate(table));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("modifyDictionary error", e);
            throw new TableException(e);
        }
    }

    @Override
    public boolean modifyTableName(String tableName, String newName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getModifyTableName(tableName, newName);
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            throw new TableException("modifyTableName error ,tableName:" + tableName, e);
        }
    }

    @Override
    public boolean deleteTableById(String id) throws TableException {
        String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEBYID);
        try {
            int count = jdbcTemplate.update(sql, new Object[]{id});
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteTableById error ,id:" + id, e);
        }
    }

    @Override
    public List<FeepTable> queryFeepTable(FeepQueryBean feepQueryBean) throws TableException {
        List<FeepTable> result;
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            feepQueryBean.setModuleName(TABLENAME);
            feepQueryBean.setFields(null);
            FeepSQL sql = basicSqlBuild.getQuerySQL(feepQueryBean);
            if (FeepUtil.isNull(sql.getParams())) {
                result = jdbcTemplate.query(sql.getSql(), FeepEntityRowMapper.getMapper(FeepTable.class));
            } else {
                result = jdbcTemplate.query(sql.getSql(), sql.getParams(), FeepEntityRowMapper.getMapper(FeepTable.class));
            }
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("queryFeepTable error", e);
        }
    }

    @Override
    public int countFeepTable() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_COUNTTABLE);
            Map<String, Object> map = jdbcTemplate.queryForMap(sql);
            return Integer.valueOf(map.get("count").toString());
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean removeTable(String tableName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            jdbcTemplate.execute(basicSqlBuild.getRemoveTableSql(tableName));
            return false;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }
}
