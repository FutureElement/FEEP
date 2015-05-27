package com.feit.feep.dbms.build;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.entity.SafeQuestion;

public class FeepEntityRowMapper {

    private FeepEntityRowMapper(){

    }

    private static Map<String, Integer> map;

    private static final int            INDEX_FEEPUSER     = 0;
    private static final int            INDEX_SAFEQUESTION = 1;

    static {
        map = new HashMap<String, Integer>();
        map.put(FeepUser.class.getCanonicalName(), INDEX_FEEPUSER);
        map.put(SafeQuestion.class.getCanonicalName(), INDEX_SAFEQUESTION);
    }

    @SuppressWarnings("unchecked")
    public static <T> RowMapper<T> getMapper(Class<T> classType) {
        switch (map.get(classType.getCanonicalName())) {
            case INDEX_FEEPUSER:
                return (RowMapper<T>) new FeepUserRowMapper();
            case INDEX_SAFEQUESTION:
                return (RowMapper<T>) new SafeQuestionRowMapper();
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

}
