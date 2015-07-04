package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.dao.IFeepModuleFieldDao;
import com.feit.feep.dbms.entity.module.FeepModuleField;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据模型字段dao实现
 * Created by zhanggang on 2015/7/3.
 */
@Repository
public class FeepModuleFieldDao implements IFeepModuleFieldDao {

    private static final String KEY_ADDMODULEFIELD = "sql.dbms.moduleField.addModuleField";
    private static final String KEY_DELETEMODULEFIELDBYID = "sql.dbms.moduleField.deleteModuleFieldById";
    private static final String KEY_DELETEMODULEFIELDBYIDS = "sql.dbms.moduleField.deleteModuleFieldByIds";
    private static final String KEY_DELETEMODULEFIELDBYMODULEID = "sql.dbms.moduleField.deleteModuleFieldByModuleId";
    private static final String KEY_UPDATEMODULEFIELD = "sql.dbms.moduleField.updateModuleField";
    private static final String KEY_FINDMODULEFIELDSBYMODULEID = "sql.dbms.moduleField.findModuleFieldsByModuleId";
    private static final String KEY_FINDMODULEFIELDBYID = "sql.dbms.moduleField.findModuleFieldById";
    private static final String KEY_FINDALL = "sql.dbms.moduleField.findAll";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addModuleField(FeepModuleField moduleField) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDMODULEFIELD);
            if (FeepUtil.isNull(moduleField.getId())) moduleField.setId(FeepUtil.getUUID());
            jdbcTemplate.update(sql, convertFeepModuleFieldToParameterForInsert(moduleField));
            return moduleField.getId();
        } catch (Exception e) {
            throw new TableException("addModuleField [" + moduleField.getName() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertFeepModuleFieldToParameterForInsert(FeepModuleField moduleField) {
        return new Object[]{moduleField.getId(), moduleField.getName(), moduleField.getShowname(), moduleField.getCode(), moduleField.getSort(), moduleField.getSearchable(), moduleField.getModuleid(), moduleField.getTablefieldid()};
    }

    private Object[] convertFeepModuleFieldToParameterForUpdate(FeepModuleField moduleField) {
        return new Object[]{moduleField.getName(), moduleField.getShowname(), moduleField.getCode(), moduleField.getSort(), moduleField.getSearchable(), moduleField.getModuleid(), moduleField.getTablefieldid(), moduleField.getId()};
    }

    @Override
    public String[] addModuleFields(List<FeepModuleField> moduleFields) throws TableException {
        try {
            if (!FeepUtil.isNull(moduleFields)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDMODULEFIELD);
                List<Object[]> data = new LinkedList<Object[]>();
                List<String> ids = new LinkedList<String>();
                for (FeepModuleField moduleField : moduleFields) {
                    if (FeepUtil.isNull(moduleField.getId())) moduleField.setId(FeepUtil.getUUID());
                    data.add(convertFeepModuleFieldToParameterForInsert(moduleField));
                    ids.add(moduleField.getId());
                }
                jdbcTemplate.batchUpdate(sql, data);
                return ids.toArray(new String[ids.size()]);
            }
            return null;
        } catch (Exception e) {
            throw new TableException("addModuleFields [" + moduleFields.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteModuleFieldById(String id) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEMODULEFIELDBYID);
            int count = jdbcTemplate.update(sql, id);
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteModuleFieldById error ,id:" + id, e);
        }
    }

    @Override
    public boolean deleteModuleFieldByIds(String[] ids) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEMODULEFIELDBYIDS);
            int count = jdbcTemplate.update(sql + " (" + GeneratorSqlBuild.convertArrayToSqlString(ids) + ")");
            return count == ids.length;
        } catch (Exception e) {
            throw new TableException("deleteModuleFieldByIds error ,ids:" + FeepUtil.toString(ids), e);
        }
    }

    @Override
    public boolean deleteModuleFieldByModuleId(String moduleId) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEMODULEFIELDBYMODULEID);
            jdbcTemplate.update(sql, moduleId);
            return true;
        } catch (Exception e) {
            throw new TableException("deleteModuleFieldByModuleId error, moduleId:" + moduleId, e);
        }
    }

    @Override
    public boolean updateModuleField(FeepModuleField moduleField) throws TableException {
        try {
            int i = 0;
            if (null != moduleField && !FeepUtil.isNull(moduleField.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATEMODULEFIELD);
                i = jdbcTemplate.update(sql, convertFeepModuleFieldToParameterForUpdate(moduleField));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("updateModuleField error", e);
            throw new TableException(e);
        }
    }

    @Override
    public boolean batchUpdateModuleFields(List<FeepModuleField> moduleFields) throws TableException {
        try {
            if (!FeepUtil.isNull(moduleFields)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATEMODULEFIELD);
                List<Object[]> data = new LinkedList<Object[]>();
                for (FeepModuleField moduleField : moduleFields) {
                    data.add(convertFeepModuleFieldToParameterForUpdate(moduleField));
                }
                jdbcTemplate.batchUpdate(sql, data);
            }
            return true;
        } catch (Exception e) {
            throw new TableException("batchUpdateModuleFields [" + moduleFields.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public List<FeepModuleField> findModuleFieldsByModuleId(String moduleId) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDMODULEFIELDSBYMODULEID);
            List<FeepModuleField> result = jdbcTemplate.query(sql, new Object[]{moduleId}, FeepEntityRowMapper.getInstance(FeepModuleField.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("findModuleFieldsByModuleId error, moduleId:" + moduleId, e);
        }
    }

    @Override
    public FeepModuleField findModuleFieldById(String id) throws TableException {
        FeepModuleField moduleField = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDMODULEFIELDBYID);
            List<FeepModuleField> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getInstance(FeepModuleField.class));
            if (null != result) {
                moduleField = result.get(0);
            }
            return moduleField;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public List<FeepModuleField> findAll() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDALL);
            List<FeepModuleField> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getInstance(FeepModuleField.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }
}
