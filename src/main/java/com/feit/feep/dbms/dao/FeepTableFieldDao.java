package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/17 0017.
 */
@Repository
public class FeepTableFieldDao implements IFeepTableFieldDao {

    private static final String KEY_INSERTTABLEFIELD = "sql.dbms.tableField.insertTableField";
    private static final String KEY_DELETETABLEFIELDBYID = "sql.dbms.tableField.deleteTableFieldById";
    private static final String KEY_DELETETABLEFIELDSBYIDS = "sql.dbms.tableField.deleteTableFieldsByIds";
    private static final String KEY_GETFEEPTABLEFIELDBYTABLEID = "sql.dbms.tableField.getFeepTableFieldByTableId";
    private static final String KEY_FINDFEEPTABLEFIELDBYID = "sql.dbms.tableField.findFeepTableFieldById";
    private static final String KEY_CLEANFIELDDATA = "sql.dbms.tableField.cleanFieldData";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String insertTableField(FeepTableField feepTableField) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_INSERTTABLEFIELD);
            if (FeepUtil.isNull(feepTableField.getId())) feepTableField.setId(FeepUtil.getUUID());
            jdbcTemplate.update(sql, convertFeepTableFieldToParameter(feepTableField));
            return feepTableField.getId();
        } catch (Exception e) {
            throw new TableException("insertTableFields [" + feepTableField.getName() + "] error, " + e.getMessage(), e);
        }
    }


    private Object[] convertFeepTableFieldToParameter(FeepTableField feepTableField) {
        return new Object[]{feepTableField.getId(), feepTableField.getName(), feepTableField.getShowname(), feepTableField.getDatatype(), feepTableField.getRange(), feepTableField.getPrecision(), feepTableField.isNotnull(), feepTableField.isUnique(), feepTableField.getTableid()};
    }

    @Override
    public String[] insertTableFields(List<FeepTableField> tableFieldList) throws TableException {
        try {
            if (!FeepUtil.isNull(tableFieldList)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_INSERTTABLEFIELD);
                List<Object[]> data = new LinkedList<Object[]>();
                List<String> ids = new LinkedList<String>();
                for (FeepTableField feepTableField : tableFieldList) {
                    if (FeepUtil.isNull(feepTableField.getId())) feepTableField.setId(FeepUtil.getUUID());
                    data.add(convertFeepTableFieldToParameter(feepTableField));
                    ids.add(feepTableField.getId());
                }
                jdbcTemplate.batchUpdate(sql, data);
                return ids.toArray(new String[ids.size()]);
            }
            return null;
        } catch (Exception e) {
            throw new TableException("insertTableFields [" + tableFieldList.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteTableFieldById(String id) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEFIELDBYID);
            int count = jdbcTemplate.update(sql, new Object[]{id});
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteTableFieldById error ,id:" + id, e);
        }
    }

    @Override
    public boolean deleteTableFieldsByIds(String[] ids) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEFIELDSBYIDS);
            StringBuilder buff = new StringBuilder(sql);
            buff.append(" (");
            buff.append(BasicSqlBuild.convertArrayToSqlString(ids));
            buff.append(")");
            int count = jdbcTemplate.update(buff.toString());
            return count == ids.length;
        } catch (Exception e) {
            throw new TableException("deleteTableFieldsByIds error ,ids:" + FeepUtil.toString(ids), e);
        }
    }

    @Override
    public List<FeepTableField> getFeepTableFieldByTableId(String tableId) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_GETFEEPTABLEFIELDBYTABLEID);
            List<FeepTableField> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getMapper(FeepTableField.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("getFeepTableFieldByTableId error, tableId:" + tableId, e);
        }
    }

    @Override
    public FeepTableField findFeepTableFieldById(String id) throws TableException {
        FeepTableField feepTableField = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDFEEPTABLEFIELDBYID);
            List<FeepTableField> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getMapper(FeepTableField.class));
            if (null != result) {
                feepTableField = result.get(0);
            }
            return feepTableField;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean removeTableColumn(String tableName, String fieldName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getRemoveColumnSQL(tableName, fieldName);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean addTableColumn(String tableName, FeepTableField feepTableField) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getAddColumnSQL(tableName, feepTableField.getName(), FieldType.valueOf(feepTableField.getDatatype()), feepTableField.getRange(), feepTableField.getPrecision());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean addNotNullConstraint(String tableName, String fieldName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getAddNotNullConstraint(tableName, fieldName);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean addUniqueConstraint(String tableName, String fieldName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getAddUniqueConstraintSQL(tableName, fieldName);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean removeNotNullConstraint(String tableName, String fieldName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getRemoveNotNullConstraint(tableName, fieldName);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean removeUniqueConstraint(String tableName, String fieldName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getRemoveUniqueConstraintSQL(tableName, fieldName);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean modifyTableColumnName(String tableName, String fieldName, String newName) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getModifyTableColumnName(tableName, fieldName, newName);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean modifyTableColumnRange(String tableName, FeepTableField feepTableField) throws TableException {
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            String sql = basicSqlBuild.getModifyTableColumnRangeSQL(tableName, feepTableField.getName(), FieldType.valueOf(feepTableField.getDatatype()), feepTableField.getRange(), feepTableField.getPrecision());
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }


}
