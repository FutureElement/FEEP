package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.entity.EntityBean;
import com.feit.feep.dbms.entity.datasource.FieldType;
import com.feit.feep.dbms.entity.module.FeepTableField;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/17 0017.
 */
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
    public List<EntityBean> getFeepTableFieldByTableId(String tableId) throws TableException {
        return null;
    }

    @Override
    public FeepTableField findFeepTableFieldById(String id) throws TableException {

        return null;
    }

    @Override
    public boolean removeTableColumn(FeepTableField feepTableField) throws TableException {
        return false;
    }

    @Override
    public boolean addTableColumn(FeepTableField feepTableField) throws TableException {
        return false;
    }

    @Override
    public boolean cleanFieldData(FeepTableField feepTableField) throws TableException {
        return false;
    }

    @Override
    public boolean addNotNullConstraint(String tableName, String fieldName) throws TableException {
        return false;
    }

    @Override
    public boolean addUniqueConstraint(String tableName, String fieldName) throws TableException {
        return false;
    }

    @Override
    public boolean removeNotNullConstraint(String tableName, String fieldName) throws TableException {
        return false;
    }

    @Override
    public boolean removeUniqueConstraint(String tableName, String fieldName) throws TableException {
        return false;
    }

    @Override
    public boolean modifyTableColumnName(String tableName, String fieldName, String newName) throws TableException {
        return false;
    }

    @Override
    public boolean modifyTableColumnType(String tableName, String fieldName, FieldType type, int range, int precision) throws TableException {
        return false;
    }
}
