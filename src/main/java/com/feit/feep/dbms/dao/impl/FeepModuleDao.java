package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.dao.IFeepModuleDao;
import com.feit.feep.dbms.entity.module.FeepModule;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.FeepSQL;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据模型dao实现类
 * Created by zhanggang on 2015/7/2.
 */
@Repository
public class FeepModuleDao implements IFeepModuleDao {

    private static final String TABLENAME = "feep_module";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String KEY_ADDMODULE = "sql.dbms.module.addModule";
    private static final String KEY_DELETEMODULEBYID = "sql.dbms.module.deleteModuleById";
    private static final String KEY_UPDATEMODULE = "sql.dbms.module.updateModule";
    private static final String KEY_FINDMODULEBYID = "sql.dbms.module.findModuleById";
    private static final String KEY_FINDALL = "sql.dbms.module.findAll";

    @Override
    public String addModule(FeepModule feepModule) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDMODULE);
            if (FeepUtil.isNull(feepModule.getId())) {
                feepModule.setId(FeepUtil.getUUID());
            }
            jdbcTemplate.update(sql, convertFeepModuleToParameterForInsert(feepModule));
            return feepModule.getId();
        } catch (Exception e) {
            throw new TableException("addModule [" + feepModule.getName() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertFeepModuleToParameterForInsert(FeepModule feepModule) {
        return new Object[]{feepModule.getId(), feepModule.getName(), feepModule.getShowname(), feepModule.getDescription()};
    }

    private Object[] convertFeepModuleToParameterForUpdate(FeepModule feepModule) {
        return new Object[]{feepModule.getName(), feepModule.getShowname(), feepModule.getDescription(), feepModule.getId()};
    }

    @Override
    public boolean deleteModuleById(String id) throws TableException {
        String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEMODULEBYID);
        try {
            int count = jdbcTemplate.update(sql, id);
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteModuleById error ,id:" + id, e);
        }
    }

    @Override
    public boolean updateModule(FeepModule feepModule) throws TableException {
        try {
            int i = 0;
            if (null != feepModule && !FeepUtil.isNull(feepModule.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATEMODULE);
                i = jdbcTemplate.update(sql, convertFeepModuleToParameterForUpdate(feepModule));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("updateModule error", e);
            throw new TableException(e);
        }
    }

    @Override
    public FeepModule findModuleById(String id) throws TableException {
        FeepModule feepModule = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDMODULEBYID);
            List<FeepModule> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getInstance(FeepModule.class));
            if (null != result) {
                feepModule = result.get(0);
            }
            return feepModule;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public List<FeepModule> queryModule(FeepQueryBean queryBean) throws TableException {
        List<FeepModule> result;
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            queryBean.setModuleName(TABLENAME);
            queryBean.setFields(null);
            FeepSQL sql = basicSqlBuild.getQuerySQL(queryBean);
            if (FeepUtil.isNull(sql.getParams())) {
                result = jdbcTemplate.query(sql.getSql(), FeepEntityRowMapper.getInstance(FeepModule.class));
            } else {
                result = jdbcTemplate.query(sql.getSql(), sql.getParams(), FeepEntityRowMapper.getInstance(FeepModule.class));
            }
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("queryModule error", e);
        }
    }

    @Override
    public List<FeepModule> findAll() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDALL);
            List<FeepModule> result = jdbcTemplate.query(sql, FeepEntityRowMapper.getInstance(FeepModule.class));
            return null == result ? null : result;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }
}
