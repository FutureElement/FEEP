package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.dao.IFeepTableFieldRelationDao;
import com.feit.feep.dbms.entity.module.FeepTableFieldRelation;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;


/**
 * 数据字典关联dao实现类
 * Created by zhanggang on 2015/7/3.
 */
@Repository
public class FeepTableFieldRelationDao implements IFeepTableFieldRelationDao {

    private static final String KEY_ADDTABLEFIELDRELATION = "sql.dbms.tableFieldRelation.addTableFieldRelation";
    private static final String KEY_DELETETABLEFIELDRELATIONBYID = "sql.dbms.tableFieldRelation.deleteTableFieldRelationById";
    private static final String KEY_DELETETABLEFIELDRELATIONBYIDS = "sql.dbms.tableFieldRelation.deleteTableFieldRelationByIds";
    private static final String KEY_DELETETABLEFIELDRELATIONBYRELATIONID = "sql.dbms.tableFieldRelation.deleteTableFieldRelationByRelationId";
    private static final String KEY_UPDATETABLEFIELDRELATION = "sql.dbms.tableFieldRelation.updateTableFieldRelation";
    private static final String KEY_FINDALL = "sql.dbms.tableFieldRelation.findAll";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addTableFieldRelation(FeepTableFieldRelation tableFieldRelation) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDTABLEFIELDRELATION);
            if (FeepUtil.isNull(tableFieldRelation.getId())) tableFieldRelation.setId(FeepUtil.getUUID());
            jdbcTemplate.update(sql, convertFeepTableFieldRelationToParameterForInsert(tableFieldRelation));
            return tableFieldRelation.getId();
        } catch (Exception e) {
            throw new TableException("addTableFieldRelation tablemodulerelationid[" + tableFieldRelation.getTablemodulerelationid() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertFeepTableFieldRelationToParameterForInsert(FeepTableFieldRelation tableFieldRelation) {
        return new Object[]{tableFieldRelation.getId(), tableFieldRelation.getTablemodulerelationid(), tableFieldRelation.getMainmodulefieldid(), tableFieldRelation.getSubtablefieldid(), tableFieldRelation.getCondition()};
    }

    private Object[] convertFeepTableFieldRelationToParameterForUpdate(FeepTableFieldRelation tableFieldRelation) {
        return new Object[]{tableFieldRelation.getTablemodulerelationid(), tableFieldRelation.getMainmodulefieldid(), tableFieldRelation.getSubtablefieldid(), tableFieldRelation.getCondition(), tableFieldRelation.getId()};
    }

    @Override
    public String[] addTableFieldRelations(List<FeepTableFieldRelation> tableFieldRelationList) throws TableException {
        try {
            if (!FeepUtil.isNull(tableFieldRelationList)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDTABLEFIELDRELATION);
                List<Object[]> data = new LinkedList<Object[]>();
                List<String> ids = new LinkedList<String>();
                for (FeepTableFieldRelation tableFieldRelation : tableFieldRelationList) {
                    if (FeepUtil.isNull(tableFieldRelation.getId())) tableFieldRelation.setId(FeepUtil.getUUID());
                    data.add(convertFeepTableFieldRelationToParameterForInsert(tableFieldRelation));
                    ids.add(tableFieldRelation.getId());
                }
                jdbcTemplate.batchUpdate(sql, data);
                return ids.toArray(new String[ids.size()]);
            }
            return null;
        } catch (Exception e) {
            throw new TableException("addTableFieldRelations [" + tableFieldRelationList.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteTableFieldRelationById(String id) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEFIELDRELATIONBYID);
            int count = jdbcTemplate.update(sql, id);
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteTableFieldRelationById error ,id:" + id, e);
        }
    }

    @Override
    public boolean deleteTableFieldRelationByIds(String[] ids) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEFIELDRELATIONBYIDS);
            int count = jdbcTemplate.update(sql + " (" + GeneratorSqlBuild.convertArrayToSqlString(ids) + ")");
            return count == ids.length;
        } catch (Exception e) {
            throw new TableException("deleteTableFieldRelationByIds error ,ids:" + FeepUtil.toString(ids), e);
        }
    }

    @Override
    public boolean deleteTableFieldRelationByRelationId(String tableFieldRelationid) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETETABLEFIELDRELATIONBYRELATIONID);
            jdbcTemplate.update(sql, tableFieldRelationid);
            return true;
        } catch (Exception e) {
            throw new TableException("deleteTableFieldRelationByRelationId error, tableFieldRelationid:" + tableFieldRelationid, e);
        }
    }

    @Override
    public boolean updateTableFieldRelation(FeepTableFieldRelation tableFieldRelation) throws TableException {
        try {
            int i = 0;
            if (null != tableFieldRelation && !FeepUtil.isNull(tableFieldRelation.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATETABLEFIELDRELATION);
                i = jdbcTemplate.update(sql, convertFeepTableFieldRelationToParameterForUpdate(tableFieldRelation));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("updateTableFieldRelation error", e);
            throw new TableException(e);
        }
    }

    @Override
    public boolean batchUpdateTableFieldRelation(List<FeepTableFieldRelation> tableFieldRelationList) throws TableException {
        try {
            if (!FeepUtil.isNull(tableFieldRelationList)) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATETABLEFIELDRELATION);
                List<Object[]> data = new LinkedList<Object[]>();
                for (FeepTableFieldRelation tableFieldRelation : tableFieldRelationList) {
                    data.add(convertFeepTableFieldRelationToParameterForUpdate(tableFieldRelation));
                }
                jdbcTemplate.batchUpdate(sql, data);
            }
            return true;
        } catch (Exception e) {
            throw new TableException("batchUpdateTableFieldRelation [" + tableFieldRelationList.size() + "] error, " + e.getMessage(), e);
        }
    }

    @Override
    public List<FeepTableFieldRelation> findAll() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDALL);
            List<FeepTableFieldRelation> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getInstance(FeepTableFieldRelation.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }
}
