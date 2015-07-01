package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.dao.IFeepDataSourceDao;
import com.feit.feep.dbms.entity.module.FeepDataSource;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据源Dao实现类
 * Created by zhanggang on 2015/7/1.
 */
@Repository
public class FeepDataSourceDao implements IFeepDataSourceDao {

    private static final String KEY_ADDDATASOURCE = "sql.dbms.dataSource.addDataSource";
    private static final String KEY_DELETEDATASOURCE = "sql.dbms.dataSource.deleteDataSource";
    private static final String KEY_UPDATEDATASOURCE = "sql.dbms.dataSource.updateDataSource";
    private static final String KEY_FINDALL = "sql.dbms.dataSource.findAll";
    private static final String KEY_FINDDATASOURCEBYID = "sql.dbms.dataSource.findDataSourceById";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addDataSource(FeepDataSource dataSource) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDDATASOURCE);
            if (FeepUtil.isNull(dataSource.getId())) {
                dataSource.setId(FeepUtil.getUUID());
            }
            jdbcTemplate.update(sql, convertDataSourceToParameterForInsert(dataSource));
            return dataSource.getId();
        } catch (Exception e) {
            throw new TableException("addDataSource [" + dataSource.getName() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertDataSourceToParameterForInsert(FeepDataSource dataSource) {
        return new Object[]{dataSource.getId(), dataSource.getName(), dataSource.getShowname(), dataSource.getDialect(), dataSource.getIp(), dataSource.getPort(), dataSource.getUsername(), dataSource.getPassword()};
    }

    private Object[] convertDataSourceToParameterForUpdate(FeepDataSource dataSource) {
        return new Object[]{dataSource.getName(), dataSource.getShowname(), dataSource.getDialect(), dataSource.getIp(), dataSource.getPort(), dataSource.getUsername(), dataSource.getPassword(), dataSource.getId()};
    }

    @Override
    public boolean deleteDataSource(String id) throws TableException {
        String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEDATASOURCE);
        try {
            int count = jdbcTemplate.update(sql, new Object[]{id});
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteDataSource error ,id:" + id, e);
        }
    }

    @Override
    public boolean updateDataSource(FeepDataSource dataSource) throws TableException {
        try {
            int i = 0;
            if (null != dataSource && !FeepUtil.isNull(dataSource.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATEDATASOURCE);
                i = jdbcTemplate.update(sql, convertDataSourceToParameterForUpdate(dataSource));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("updateDataSource error", e);
            throw new TableException(e);
        }
    }

    @Override
    public List<FeepDataSource> findAll() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDALL);
            List<FeepDataSource> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getMapper(FeepDataSource.class));
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public FeepDataSource findDataSourceById(String id) throws TableException {
        FeepDataSource dataSource = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDDATASOURCEBYID);
            List<FeepDataSource> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getMapper(FeepDataSource.class));
            if (null != result) {
                dataSource = result.get(0);
            }
            return dataSource;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }
}
