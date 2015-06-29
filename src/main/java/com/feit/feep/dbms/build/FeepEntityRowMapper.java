package com.feit.feep.dbms.build;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.module.FeepTable;
import com.feit.feep.dbms.entity.module.FeepTableField;
import org.springframework.jdbc.core.RowMapper;

import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.entity.SafeQuestion;

public class FeepEntityRowMapper {

    private FeepEntityRowMapper() {

    }

    private static Map<String, Integer> map;

    private static final int INDEX_FEEPUSER = 0;
    private static final int INDEX_SAFEQUESTION = 1;
    private static final int INDEX_FEEPTABLE = 2;
    private static final int INDEX_FEEPTABLEFIELD = 3;
    private static final int INDEX_DICTIONARY = 4;
    private static final int INDEX_DICTIONARYITEM = 5;

    static {
        map = new HashMap<String, Integer>();
        map.put(FeepUser.class.getCanonicalName(), INDEX_FEEPUSER);
        map.put(SafeQuestion.class.getCanonicalName(), INDEX_SAFEQUESTION);
        map.put(FeepTable.class.getCanonicalName(), INDEX_FEEPTABLE);
        map.put(FeepTableField.class.getCanonicalName(), INDEX_FEEPTABLEFIELD);
        map.put(Dictionary.class.getCanonicalName(), INDEX_DICTIONARY);
        map.put(DictionaryItem.class.getCanonicalName(), INDEX_DICTIONARYITEM);
    }

    @SuppressWarnings("unchecked")
    public static <T> RowMapper<T> getMapper(Class<T> classType) {
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
            default:
                return null;
        }
    }

    private static class FeepUserRowMapper implements RowMapper<FeepUser> {
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

    private static class SafeQuestionRowMapper implements RowMapper<SafeQuestion> {
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

    private static class FeepTableRowMapper implements RowMapper<FeepTable> {
        @Override
        public FeepTable mapRow(ResultSet rs, int i) throws SQLException {
            FeepTable feepTable = new FeepTable();
            feepTable.setId(rs.getString("id"));
            feepTable.setName(rs.getString("name"));
            feepTable.setDatasourceid(rs.getString("datasourceid"));
            feepTable.setDescription(rs.getString("description"));
            feepTable.setShowname(rs.getString("showname"));
            feepTable.setTabletype(rs.getString("tabletype"));
            return feepTable;
        }
    }

    private static class FeepTableFieldRowMapper implements RowMapper<FeepTableField> {
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

    private static class DictionaryRowMapper implements RowMapper<Dictionary> {
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

    private static class DictionaryItemRowMapper implements RowMapper<DictionaryItem> {
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

}
