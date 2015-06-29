package com.feit.feep.dbms.dao.impl;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.dao.IFeepDictionaryDao;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.FeepSQL;
import com.feit.feep.exception.dbms.TableException;
import com.feit.feep.util.FeepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据字典dao实现类
 * Created by ZhangGang on 2015/6/28 0028.
 */
@Repository
public class FeepDictionaryDao implements IFeepDictionaryDao {

    private static final String TABLENAME = "feep_dictionary";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String KEY_ADDDICTIONARY = "sql.dbms.dictionary.addDictionary";
    private static final String KEY_MODIFYDICTIONARY = "sql.dbms.dictionary.modifyDictionary";
    private static final String KEY_FINDDICTIONARYBYID = "sql.dbms.dictionary.findDictionaryById";
    private static final String KEY_DELETEDICTIONARYBYID = "sql.dbms.dictionary.deleteDictionaryById";
    private static final String KEY_GETTOTALCOUNT = "sql.dbms.dictionary.getTotalCount";

    @Override
    public String addDictionary(Dictionary dictionary) throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_ADDDICTIONARY);
            if (FeepUtil.isNull(dictionary.getId())) {
                dictionary.setId(FeepUtil.getUUID());
            }
            jdbcTemplate.update(sql, convertFeepDictionaryToParameterForInsert(dictionary));
            return dictionary.getId();
        } catch (Exception e) {
            throw new TableException("addDictionary [" + dictionary.getDictionaryname() + "] error, " + e.getMessage(), e);
        }
    }

    private Object[] convertFeepDictionaryToParameterForInsert(Dictionary dictionary) {
        return new Object[]{dictionary.getId(), dictionary.getDictionaryname(), dictionary.getShowname(), dictionary.getDescription()};
    }

    private Object[] convertFeepDictionaryToParameterForUpdate(Dictionary dictionary) {
        return new Object[]{dictionary.getDictionaryname(), dictionary.getShowname(), dictionary.getDescription(), dictionary.getId()};
    }

    @Override
    public boolean modifyDictionary(Dictionary dictionary) throws TableException {
        try {
            int i = 0;
            if (null != dictionary && !FeepUtil.isNull(dictionary.getId())) {
                String sql = GeneratorSqlBuild.getSqlByKey(KEY_MODIFYDICTIONARY);
                i = jdbcTemplate.update(sql, convertFeepDictionaryToParameterForUpdate(dictionary));
            }
            return i == 1;
        } catch (Exception e) {
            Global.getInstance().logError("modifyDictionary error", e);
            throw new TableException(e);
        }
    }

    @Override
    public Dictionary findDictionaryById(String id) throws TableException {
        Dictionary dictionary = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDDICTIONARYBYID);
            List<Dictionary> result = jdbcTemplate.query(sql, new Object[]{id}, FeepEntityRowMapper.getMapper(Dictionary.class));
            if (null != result) {
                dictionary = result.get(0);
            }
            return dictionary;
        } catch (Exception e) {
            throw new TableException(e);
        }
    }

    @Override
    public boolean deleteDictionaryById(String id) throws TableException {
        String sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEDICTIONARYBYID);
        try {
            int count = jdbcTemplate.update(sql, new Object[]{id});
            return count == 1;
        } catch (Exception e) {
            throw new TableException("deleteDictionaryById error ,id:" + id, e);
        }
    }

    @Override
    public List<Dictionary> queryDictionary(FeepQueryBean queryBean) throws TableException {
        List<Dictionary> result;
        try {
            BasicSqlBuild basicSqlBuild = new BasicSqlBuild();
            queryBean.setModuleName(TABLENAME);
            queryBean.setFields(null);
            FeepSQL sql = basicSqlBuild.getQuerySQL(queryBean);
            if (FeepUtil.isNull(sql.getParams())) {
                result = jdbcTemplate.query(sql.getSql(), FeepEntityRowMapper.getMapper(Dictionary.class));
            } else {
                result = jdbcTemplate.query(sql.getSql(), sql.getParams(), FeepEntityRowMapper.getMapper(Dictionary.class));
            }
            return FeepUtil.isNull(result) ? null : result;
        } catch (Exception e) {
            throw new TableException("queryDictionary error", e);
        }
    }

    @Override
    public int getTotalCount() throws TableException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_GETTOTALCOUNT);
            Map<String, Object> map = jdbcTemplate.queryForMap(sql);
            return Integer.valueOf(map.get("count").toString());
        } catch (Exception e) {
            throw new TableException(e);
        }
    }
}
