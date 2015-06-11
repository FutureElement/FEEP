package com.feit.feep.dbms.table;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.build.TableSqlBuild;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.dbms.TableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Autowired
    private TransactionTemplate transactionTemplate;

    private static final String KEY_GETTABLEBYID = "sql.dbms.table.getTableById";
    private static final String KEY_DELETETABLEBYID = "sql.dbms.table.deleteTableById";
    private static final String KEY_MODIFYTABLE = "sql.dbms.table.modifyTable";
    private static final String KEY_QUERYFEEPTABLE = "sql.dbms.table.queryFeepTable";
    private static final String KEY_COUNTTABLE = "sql.dbms.table.countTable";

    @Override
    public Boolean createTable(FeepTable feepTable) {
        Global.getInstance().logInfo("create Table :" + feepTable.getName());
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                try {
                    //1.create table
                    TableSqlBuild tableSqlBuild = new TableSqlBuild();
                    jdbcTemplate.execute(tableSqlBuild.getCreateSQL(feepTable));
                    //2.insert tableField
                    List<FeepTableField> tableFields = feepTable.getTableFields();
                    if (null != tableFields && !tableFields.isEmpty()) {
                        //TODO 调用 tableFieldDAO 最后写在Service中
                    }
                    return true;
                } catch (Exception e) {
                    Global.getInstance().logError("create table error", e);
                    transactionStatus.setRollbackOnly();
                    return false;
                }
            }
        });
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
        String sql = GeneratorSqlBuild.getSqlByKey(KEY_MODIFYTABLE);
        return false;
    }

    @Override
    public boolean deleteTableById(String id) throws TableException {
        String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEBYID);
        return false;
    }

    @Override
    public List<FeepTable> queryFeepTable(FeepQueryBean feepQueryBean) throws TableException {
        String sql = GeneratorSqlBuild.getSqlByKey(KEY_QUERYFEEPTABLE);
        jdbcTemplate.query(sql, FeepEntityRowMapper.getMapper(FeepTable.class));
        return null;
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
