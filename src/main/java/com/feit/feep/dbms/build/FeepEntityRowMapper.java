package com.feit.feep.dbms.build;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.module.*;
import org.springframework.jdbc.core.RowMapper;

import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.entity.SafeQuestion;

/**
 * JDBC 实体转换Mapper
 */
public class FeepEntityRowMapper {

    private static final FeepEntityRowMapper instance = new FeepEntityRowMapper();

    private static Map<String, Integer> map;

    public static <T> RowMapper<T> getInstance(Class<T> classType) {
        return instance.getMapper(classType);
    }

    private static final int INDEX_FEEPUSER = 0;
    private static final int INDEX_SAFEQUESTION = 1;
    private static final int INDEX_FEEPTABLE = 2;
    private static final int INDEX_FEEPTABLEFIELD = 3;
    private static final int INDEX_DICTIONARY = 4;
    private static final int INDEX_DICTIONARYITEM = 5;
    private static final int INDEX_FEEPDATASOURCE = 6;
    private static final int INDEX_FEEPMODULE = 7;
    private static final int INDEX_FEEPMODULEFIELD = 8;
    private static final int INDEX_FEEPTABLEMODULERELATION = 9;
    private static final int INDEX_FEEPTABLEFIELDRELATION = 10;

    private FeepEntityRowMapper() {
        map = new HashMap<String, Integer>();
        map.put(FeepUser.class.getCanonicalName(), INDEX_FEEPUSER);
        map.put(SafeQuestion.class.getCanonicalName(), INDEX_SAFEQUESTION);
        map.put(FeepTable.class.getCanonicalName(), INDEX_FEEPTABLE);
        map.put(FeepTableField.class.getCanonicalName(), INDEX_FEEPTABLEFIELD);
        map.put(Dictionary.class.getCanonicalName(), INDEX_DICTIONARY);
        map.put(DictionaryItem.class.getCanonicalName(), INDEX_DICTIONARYITEM);
        map.put(FeepDataSource.class.getCanonicalName(), INDEX_FEEPDATASOURCE);
        map.put(FeepModule.class.getCanonicalName(), INDEX_FEEPMODULE);
        map.put(FeepModuleField.class.getCanonicalName(), INDEX_FEEPMODULEFIELD);
        map.put(FeepTableModuleRelation.class.getCanonicalName(), INDEX_FEEPTABLEMODULERELATION);
        map.put(FeepTableFieldRelation.class.getCanonicalName(), INDEX_FEEPTABLEFIELDRELATION);
    }

    @SuppressWarnings("unchecked")
    private <T> RowMapper<T> getMapper(Class<T> classType) {
        switch (map.get(classType.getCanonicalName())) {
            case INDEX_FEEPUSER:
                return (RowMapper<T>) new FeepUserRowMapper();
            case INDEX_SAFEQUESTION:
                return (RowMapper<T>) new SafeQuestionRowMapper();
            case INDEX_FEEPTABLE:
                return (RowMapper<T>) new FeepTableRowMapper();
            case INDEX_FEEPTABLEFIELD:
                return (RowMapper<T>) new FeepTableFieldRowMapper();
            case INDEX_DICTIONARY:
                return (RowMapper<T>) new DictionaryRowMapper();
            case INDEX_DICTIONARYITEM:
                return (RowMapper<T>) new DictionaryItemRowMapper();
            case INDEX_FEEPDATASOURCE:
                return (RowMapper<T>) new FeepDataSourceRowMapper();
            case INDEX_FEEPMODULE:
                return (RowMapper<T>) new FeepModuleRowMapper();
            case INDEX_FEEPMODULEFIELD:
                return (RowMapper<T>) new FeepModuleFieldRowMapper();
            case INDEX_FEEPTABLEMODULERELATION:
                return (RowMapper<T>) new FeepTableModuleRelationRowMapper();
            case INDEX_FEEPTABLEFIELDRELATION:
                return (RowMapper<T>) new FeepTableFieldRelationRowMapper();
            default:
                return null;
        }
    }

    private class FeepUserRowMapper implements RowMapper<FeepUser> {
        @Override
        public FeepUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeepUser user = new FeepUser();
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setShowname(rs.getString("showname"));
            user.setType(rs.getString("type"));
            user.setIdentitycard(rs.getString("identitycard"));
            user.setBirthday(rs.getDate("birthday"));
            user.setEmail(rs.getString("email"));
            user.setAddress(rs.getString("address"));
            user.setRemarks(rs.getString("remarks"));
            user.setTel(rs.getString("tel"));
            return user;
        }
    }

    private class SafeQuestionRowMapper implements RowMapper<SafeQuestion> {
        @Override
        public SafeQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
            SafeQuestion safeQuestion = new SafeQuestion();
            safeQuestion.setId(rs.getString("id"));
            safeQuestion.setUserid(rs.getString("userid"));
            safeQuestion.setAnswer(rs.getString("answer"));
            safeQuestion.setQuestion(rs.getString("question"));
            safeQuestion.setType(rs.getString("type"));
            return safeQuestion;
        }
    }

    private class FeepTableRowMapper implements RowMapper<FeepTable> {
        @Override
        public FeepTable mapRow(ResultSet rs, int i) throws SQLException {
            FeepTable feepTable = new FeepTable();
            feepTable.setId(rs.getString("id"));
            feepTable.setName(rs.getString("name"));
            feepTable.setDatasourceid(rs.getString("datasourceid"));
            feepTable.setDescription(rs.getString("description"));
            feepTable.setShowname(rs.getString("showname"));
            feepTable.setTabletype(rs.getString("tabletype"));
            feepTable.setSystem(rs.getString("system"));
            return feepTable;
        }
    }

    private class FeepTableFieldRowMapper implements RowMapper<FeepTableField> {
        @Override
        public FeepTableField mapRow(ResultSet rs, int i) throws SQLException {
            FeepTableField feepTableField = new FeepTableField();
            feepTableField.setId(rs.getString("id"));
            feepTableField.setTableid(rs.getString("tableid"));
            feepTableField.setName(rs.getString("name"));
            feepTableField.setShowname(rs.getString("showname"));
            feepTableField.setDatatype(rs.getString("datatype"));
            feepTableField.setPrecision(rs.getInt("precision"));
            feepTableField.setRange(rs.getInt("range"));
            feepTableField.setNotnull(rs.getBoolean("isnotnull"));
            feepTableField.setUnique(rs.getBoolean("isunique"));
            return feepTableField;
        }
    }

    private class DictionaryRowMapper implements RowMapper<Dictionary> {
        @Override
        public Dictionary mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dictionary dictionary = new Dictionary();
            dictionary.setId(rs.getString("id"));
            dictionary.setDictionaryname(rs.getString("dictionaryname"));
            dictionary.setShowname(rs.getString("showname"));
            dictionary.setDescription(rs.getString("description"));
            return dictionary;
        }
    }

    private class DictionaryItemRowMapper implements RowMapper<DictionaryItem> {
        @Override
        public DictionaryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            DictionaryItem item = new DictionaryItem();
            item.setId(rs.getString("id"));
            item.setDictionaryid(rs.getString("dictionaryid"));
            item.setChildrenid(rs.getString("childrenid"));
            item.setCodeid(rs.getString("codeid"));
            item.setCodevalue(rs.getString("codevalue"));
            item.setSortnum(rs.getInt("sortnum"));
            item.setDescription(rs.getString("description"));
            return item;
        }
    }

    private class FeepDataSourceRowMapper implements RowMapper<FeepDataSource> {
        @Override
        public FeepDataSource mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeepDataSource dataSource = new FeepDataSource();
            dataSource.setId(rs.getString("id"));
            dataSource.setName(rs.getString("name"));
            dataSource.setShowname(rs.getString("showname"));
            dataSource.setDialect(rs.getInt("dialect"));
            dataSource.setIp(rs.getString("ip"));
            dataSource.setPort(rs.getString("port"));
            dataSource.setUsername(rs.getString("username"));
            dataSource.setPassword(rs.getString("password"));
            dataSource.setDbname(rs.getString("dbname"));
            dataSource.setSort(rs.getInt("sort"));
            dataSource.setType(rs.getInt("type"));
            return dataSource;
        }
    }

    private class FeepModuleRowMapper implements RowMapper<FeepModule> {
        @Override
        public FeepModule mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeepModule feepModule = new FeepModule();
            feepModule.setId(rs.getString("id"));
            feepModule.setName(rs.getString("name"));
            feepModule.setShowname(rs.getString("showname"));
            feepModule.setDescription(rs.getString("description"));
            return feepModule;
        }
    }

    private class FeepModuleFieldRowMapper implements RowMapper<FeepModuleField> {
        @Override
        public FeepModuleField mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeepModuleField moduleField = new FeepModuleField();
            moduleField.setId(rs.getString("id"));
            moduleField.setName(rs.getString("name"));
            moduleField.setShowname(rs.getString("showname"));
            moduleField.setCode(rs.getString("code"));
            moduleField.setSort(rs.getInt("sort"));
            moduleField.setSearchable(rs.getInt("searchable"));
            moduleField.setModuleid(rs.getString("moduleid"));
            moduleField.setTablefieldid(rs.getString("tablefieldid"));
            return moduleField;
        }
    }

    private class FeepTableModuleRelationRowMapper implements RowMapper<FeepTableModuleRelation> {
        @Override
        public FeepTableModuleRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeepTableModuleRelation moduleField = new FeepTableModuleRelation();
            moduleField.setId(rs.getString("id"));
            moduleField.setModuleid(rs.getString("moduleid"));
            moduleField.setTableid(rs.getString("tableid"));
            moduleField.setRelationType(rs.getInt("relationType"));
            moduleField.setTableType(rs.getInt("tableType"));
            return moduleField;
        }
    }

    private class FeepTableFieldRelationRowMapper implements RowMapper<FeepTableFieldRelation> {
        @Override
        public FeepTableFieldRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
            FeepTableFieldRelation moduleField = new FeepTableFieldRelation();
            moduleField.setId(rs.getString("id"));
            moduleField.setTablemodulerelationid(rs.getString("tablemodulerelationid"));
            moduleField.setMainmodulefieldid(rs.getString("mainmodulefieldid"));
            moduleField.setSubtablefieldid(rs.getString("subtablefieldid"));
            moduleField.setCondition(rs.getString("condition"));
            return moduleField;
        }
    }
}
