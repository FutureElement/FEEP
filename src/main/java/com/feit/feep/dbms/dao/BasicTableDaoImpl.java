package com.feit.feep.dbms.dao;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangGang on 2015/6/9 0009.
 */
@Repository
public class BasicTableDaoImpl implements IBasicTableDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String KEY_GETTABLEBYID = "sql.dbms.table.getTableById";
    private static final String KEY_DELETETABLEBYID = "sql.dbms.table.deleteTableById";
    private static final String KEY_MODIFYTABLE = "sql.dbms.table.modifyTable";
    private static final String KEY_COUNTTABLE = "sql.dbms.table.countTable";
    private static final String KEY_INSERT = "sql.dbms.table.insert";

    @Override
    public void createTable(FeepTable feepTable) throws TableException {
        Global.getInstance().logInfo("create Table :" + feepTable.getName());
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            jdbcTemplate.execute(basicSqlBuild.getCreateSQL(feepTable));
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
            jdbcTemplate.update(sql, convertFeepTableToParameter(feepTable));
            return feepTable.getId();
        } catch (Exception e) {
            throw new TableException("insertFeepTable [" + feepTable.getName() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertFeepTableToParameter(FeepTable feepTable) {
        return new Object[]{feepTable.getId(), feepTable.getName(), feepTable.getShowname(), feepTable.getTabletype(), feepTable.getDescription(), feepTable.getDatasourceid()};
    }

    @Override
    public FeepTable getTableById(String id) throws TableException {
        FeepTable feepTable = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_GETTABLEBYID);
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, new Object[]{id});
            if (null != sqlRowSet) {
                feepTable = new FeepTable();
                List<FeepTableField> list = new LinkedList<FeepTableField>();
                while (sqlRowSet.next()) {
                    if (list.size() == 0) {
                        feepTable.setId(sqlRowSet.getString("id"));
                        feepTable.setName(sqlRowSet.getString("name"));
                        feepTable.setDatasourceid(sqlRowSet.getString("datasourceid"));
                        feepTable.setDescription(sqlRowSet.getString("description"));
                        feepTable.setShowname(sqlRowSet.getString("showname"));
                        feepTable.setTabletype(sqlRowSet.getString("tabletype"));
                    }
                    FeepTableField feepTableField = new FeepTableField();
                    feepTableField.setId(sqlRowSet.getString("tablefieldid"));
                    feepTableField.setTableid(sqlRowSet.getString("tableid"));
                    feepTableField.setName(sqlRowSet.getString("tablefieldname"));
                    feepTableField.setShowname(sqlRowSet.getString("tablefieldshowname"));
                    feepTableField.setDatatype(sqlRowSet.getString("datatype"));
                    feepTableField.setPrecision(sqlRowSet.getInt("precision"));
                    feepTableField.setRange(sqlRowSet.getInt("range"));
                    feepTableField.setNotnull(sqlRowSet.getBoolean("isnotnull"));
                    feepTableField.setUnique(sqlRowSet.getBoolean("isunique"));
                    list.add(feepTableField);
                }
                feepTable.setTableFields(list);
            }
            return feepTable;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean modifyTable(FeepTable table) throws TableException {
        try {
            int i = 0;
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_MODIFYTABLE);
            StringBuilder buff = new StringBuilder();
            List<Object> argList = new LinkedList<Object>();
            buff.append(sql);
            if (null != table && !FeepUtil.isNull(table.getId())) {
                if (!FeepUtil.isNull(table.getName())) {
                    buff.append(" name=?,");
                    argList.add(table.getName());
                }
                if (!FeepUtil.isNull(table.getShowname())) {
                    buff.append(" showname=?,");
                    argList.add(table.getShowname());
                }
                if (!FeepUtil.isNull(table.getTabletype())) {
                    buff.append(" tabletype=?,");
                    argList.add(table.getTabletype());
                }
                if (!FeepUtil.isNull(table.getDescription())) {
                    buff.append(" description=?,");
                    argList.add(table.getDescription());
                }
                if (!FeepUtil.isNull(table.getDatasourceid())) {
                    buff.append(" datasourceid=?,");
                    argList.add(table.getDatasourceid());
                }
                sql = buff.substring(0, buff.length() - 1);
                sql += " WHERE id = ?";
                argList.add(table.getId());
                i = jdbcTemplate.update(sql, argList.toArray(new Object[argList.size()]));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("updateUser error", e);
            throw new TableException(e);
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
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getQuerySQL(feepQueryBean);
            List<FeepTable> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getMapper(FeepTable.class));
            return result;
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
}
