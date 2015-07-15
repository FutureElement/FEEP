package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.dao.IFeepTableModuleRelationDao;
import com.feit.feep.dbms.entity.module.FeepTableModuleRelation;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * 数据模型表关系dao实现类
 * Created by zhanggang on 2015/7/3.
 */
@Repository
public class FeepTableModuleRelationDao implements IFeepTableModuleRelationDao {

    private static final String KEY_ADDTABLEMODULERELATION = "sql.dbms.tableModuleRelation.addTableModuleRelation";
    private static final String KEY_DELETETABLEMODULERELATIONBYID = "sql.dbms.tableModuleRelation.deleteTableModuleRelationById";
    private static final String KEY_DELETETABLEMODULERELATIONBYMODULEID = "sql.dbms.tableModuleRelation.deleteTableModuleRelationByModuleId";
    private static final String KEY_UPDATETABLEMODULERELATION = "sql.dbms.tableModuleRelation.updateTableModuleRelation";
    private static final String KEY_FINDALL = "sql.dbms.tableModuleRelation.findAll";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addTableModuleRelation(FeepTableModuleRelation tableModuleRelation) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDTABLEMODULERELATION);
            if (FeepUtil.isNull(tableModuleRelation.getId())) tableModuleRelation.setId(FeepUtil.getUUID());
            jdbcTemplate.update(sql, convertFeepTableModuleRelationToParameterForInsert(tableModuleRelation));
            return tableModuleRelation.getId();
        } catch (Exception e) {
            throw new TableException("addTableModuleRelation moduleid[" + tableModuleRelation.getModuleid() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertFeepTableModuleRelationToParameterForInsert(FeepTableModuleRelation tableModuleRelation) {
        return new Object[]{tableModuleRelation.getId(), tableModuleRelation.getModuleid(), tableModuleRelation.getTableid(), tableModuleRelation.getRelationType(), tableModuleRelation.getTableType()};
    }

    private Object[] convertFeepTableModuleRelationToParameterForUpdate(FeepTableModuleRelation tableModuleRelation) {
        return new Object[]{tableModuleRelation.getModuleid(), tableModuleRelation.getTableid(), tableModuleRelation.getRelationType(), tableModuleRelation.getTableType(), tableModuleRelation.getId()};
    }

    @Override
    public String[] addTableModuleRelations(List<FeepTableModuleRelation> tableModuleRelationList) throws TableException {
        try {
            if (!FeepUtil.isNull(tableModuleRelationList)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDTABLEMODULERELATION);
                List<Object[]> data = new LinkedList<Object[]>();
                List<String> ids = new LinkedList<String>();
                for (FeepTableModuleRelation tableModuleRelation : tableModuleRelationList) {
                    if (FeepUtil.isNull(tableModuleRelation.getId())) tableModuleRelation.setId(FeepUtil.getUUID());
                    data.add(convertFeepTableModuleRelationToParameterForInsert(tableModuleRelation));
                    ids.add(tableModuleRelation.getId());
                }
                jdbcTemplate.batchUpdate(sql, data);
                return ids.toArray(new String[ids.size()]);
            }
            return null;
        } catch (Exception e) {
            throw new TableException("addTableModuleRelations [" + tableModuleRelationList.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteTableModuleRelationById(String id) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEMODULERELATIONBYID);
            int count = jdbcTemplate.update(sql, id);
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteTableModuleRelationById error ,id:" + id, e);
        }
    }

    @Override
    public boolean deleteTableModuleRelationByIds(String[] ids) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEMODULERELATIONBYID);
            List<Object[]> data = new LinkedList<Object[]>();
            for (String id : ids) {
                data.add(new Object[]{id});
            }
            jdbcTemplate.batchUpdate(sql, data);
            return true;
        } catch (Exception e) {
            throw new TableException("deleteTableModuleRelationByIds error ,ids:" + FeepUtil.toString(ids), e);
        }
    }

    @Override
    public boolean deleteTableModuleRelationByModuleId(String moduleId) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEMODULERELATIONBYMODULEID);
            jdbcTemplate.update(sql, moduleId);
            return true;
        } catch (Exception e) {
            throw new TableException("deleteTableModuleRelationByModuleId error, moduleId:" + moduleId, e);
        }
    }

    @Override
    public boolean updateTableModuleRelation(FeepTableModuleRelation tableModuleRelation) throws TableException {
        try {
            int i = 0;
            if (null != tableModuleRelation && !FeepUtil.isNull(tableModuleRelation.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATETABLEMODULERELATION);
                i = jdbcTemplate.update(sql, convertFeepTableModuleRelationToParameterForUpdate(tableModuleRelation));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("updateTableModuleRelation error", e);
            throw new TableException(e);
        }
    }

    @Override
    public boolean batchUpdateTableModuleRelation(List<FeepTableModuleRelation> tableModuleRelationList) throws TableException {
        try {
            if (!FeepUtil.isNull(tableModuleRelationList)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATETABLEMODULERELATION);
                List<Object[]> data = new LinkedList<Object[]>();
                for (FeepTableModuleRelation tableModuleRelation : tableModuleRelationList) {
                    data.add(convertFeepTableModuleRelationToParameterForUpdate(tableModuleRelation));
                }
                jdbcTemplate.batchUpdate(sql, data);
            }
            return true;
        } catch (Exception e) {
            throw new TableException("batchUpdateTableModuleRelation [" + tableModuleRelationList.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public List<FeepTableModuleRelation> findAll() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDALL);
            List<FeepTableModuleRelation> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getInstance(FeepTableModuleRelation.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }
}
