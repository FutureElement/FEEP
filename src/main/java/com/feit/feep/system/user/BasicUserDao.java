package com.feit.feep.system.user;

import java.util.LinkedList;
import java.util.List;

import com.feit.feep.dbms.build.BasicSqlBuild;
import com.feit.feep.dbms.build.GeneratorSqlBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.exception.dbms.QueryException;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.util.FeepUtil;

/**
 * 用户管理，为用户登陆，数据初始化提供支持 ，使用原生JDBC SQL 防止数据出错无法登陆
 *
 * @author ZhangGang
 */
@Repository
public class BasicUserDao implements IBasicUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String KEY_SELECTALL = "sql.common.user.selectAll";
    private static final String KEY_FINDBYID = "sql.common.user.findById";
    private static final String KEY_FINDBYUSERNAME = "sql.common.user.findByUserName";
    private static final String KEY_INSERTUSER = "sql.common.user.insertUser";
    private static final String KEY_UPDATEUSER = "sql.common.user.updateUser";

    private static final String KEY_DELETEUSERBYID = "sql.common.user.deleteUserById";
    private static final String KEY_DELETEUSERBYIDS = "sql.common.user.deleteUserByIds";
    private static final String KEY_REALDELETEUSERBYID = "sql.common.user.realDeleteUserById";
    private static final String KEY_REALDELETEUSERBYIDS = "sql.common.user.realDeleteUserByIds";

    private FeepUser getUser(String sql, String parameter) throws QueryException {
        try {
            List<FeepUser> list = jdbcTemplate.query(sql, new Object[]{parameter}, FeepEntityRowMapper.getMapper(FeepUser.class));
            if (!FeepUtil.isNull(list)) {
                return list.get(0);
            }
            return null;
        } catch (Exception e) {
            Global.getInstance().logError("BasicUserDao getUser error,sql : " + sql + " , parameter : " + parameter, e);
            throw new QueryException(e);
        }
    }

    @Override
    public FeepUser getUserById(String userid) throws QueryException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDBYID);
            return getUser(sql, userid);
        } catch (Exception e) {
            Global.getInstance().logError("find user by id error,id:" + userid, e);
            throw new QueryException(e);
        }
    }

    @Override
    public FeepUser getUserByUserName(String username) throws QueryException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_FINDBYUSERNAME);
            return getUser(sql, username);
        } catch (Exception e) {
            Global.getInstance().logError("find user by username error,username:" + username, e);
            throw new QueryException(e);
        }
    }

    @Override
    public List<FeepUser> getAllUsers() throws QueryException {
        List<FeepUser> list = null;
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_SELECTALL);
            list = jdbcTemplate.query(sql, FeepEntityRowMapper.getMapper(FeepUser.class));
        } catch (Exception e) {
            Global.getInstance().logError("select all users error", e);
            throw new QueryException(e);
        }
        return list;
    }

    @Override
    public String insertUser(FeepUser user) throws QueryException {
        try {
            if (FeepUtil.isNull(user.getId())) {
                user.setId(FeepUtil.getUUID());
            }
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_INSERTUSER);
            int i = jdbcTemplate.update(sql, convertUserToParameter(user));
            if (i == 1) {
                return user.getId();
            } else return null;
        } catch (Exception e) {
            Global.getInstance().logError("insertUser error", e);
            throw new QueryException(e);
        }
    }

    private Object[] convertUserToParameter(FeepUser user) {
        return new Object[]{user.getId(),
                user.getUsername(),
                user.getShowname(),
                user.getPassword(),
                user.getType(),
                user.getIdentitycard(),
                user.getBirthday(),
                user.getTel(),
                user.getEmail(),
                user.getAddress(),
                user.getRemarks()};
    }

    @Override
    public String[] insertUsers(List<FeepUser> users) throws QueryException {
        List<String> ids = new LinkedList<String>();
        try {
            List<Object[]> batchArgs = null;
            if (!FeepUtil.isNull(users)) {
                batchArgs = new LinkedList<Object[]>();
                for (FeepUser user : users) {
                    if (FeepUtil.isNull(user.getId())) {
                        user.setId(FeepUtil.getUUID());
                    }
                    ids.add(user.getId());
                    batchArgs.add(convertUserToParameter(user));
                }
            }
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_INSERTUSER);
            jdbcTemplate.batchUpdate(sql, batchArgs);
            return ids.toArray(new String[ids.size()]);
        } catch (Exception e) {
            Global.getInstance().logError("insert batch Users error", e);
            throw new QueryException(e);
        }
    }

    @Override
    public boolean updateUser(FeepUser user) throws QueryException {
        try {
            String sql = GeneratorSqlBuild.getSqlByKey(KEY_UPDATEUSER);
            StringBuilder buff = new StringBuilder();
            List<Object> argList = new LinkedList<Object>();
            buff.append(sql);
            if (null != user && !FeepUtil.isNull(user.getId())) {
                if (!FeepUtil.isNull(user.getShowname())) {
                    buff.append(" showname=?,");
                    argList.add(user.getShowname());
                }
                if (!FeepUtil.isNull(user.getPassword())) {
                    buff.append(" password=?,");
                    argList.add(user.getPassword());
                }
                if (!FeepUtil.isNull(user.getType())) {
                    buff.append(" type=?,");
                    argList.add(user.getType());
                }
                if (!FeepUtil.isNull(user.getIdentitycard())) {
                    buff.append(" identitycard=?,");
                    argList.add(user.getIdentitycard());
                }
                if (null != user.getBirthday()) {
                    buff.append(" birthday=?,");
                    argList.add(user.getBirthday());
                }
                if (!FeepUtil.isNull(user.getTel())) {
                    buff.append(" tel=?,");
                    argList.add(user.getTel());
                }
                if (!FeepUtil.isNull(user.getEmail())) {
                    buff.append(" email=?,");
                    argList.add(user.getEmail());
                }
                if (!FeepUtil.isNull(user.getAddress())) {
                    buff.append(" address=?,");
                    argList.add(user.getAddress());
                }
                if (!FeepUtil.isNull(user.getRemarks())) {
                    buff.append(" remarks=?,");
                    argList.add(user.getRemarks());
                }
                sql = buff.substring(0, buff.length() - 1);
                sql += " WHERE id = ?";
                argList.add(user.getId());
                int i = jdbcTemplate.update(sql, argList.toArray(new Object[argList.size()]));
                if (i == 1) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Global.getInstance().logError("updateUser error", e);
            throw new QueryException(e);
        }
    }

    @Override
    public boolean deleteUserById(String id, boolean isRealDelete) throws QueryException {
        try {
            String sql = null;
            if (isRealDelete) {
                sql = GeneratorSqlBuild.getSqlByKey(KEY_REALDELETEUSERBYID);
            } else {
                sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEUSERBYID);
            }
            int i = jdbcTemplate.update(sql, new Object[]{id});
            if (i == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Global.getInstance().logError("deleteUserById error", e);
            throw new QueryException(e);
        }
    }

    @Override
    public boolean deleteUserByIds(String[] ids, boolean isRealDelete) throws QueryException {
        try {
            if (null == ids || ids.length == 0) {
                return false;
            }
            String sql = null;
            if (isRealDelete) {
                sql = GeneratorSqlBuild.getSqlByKey(KEY_REALDELETEUSERBYIDS);
            } else {
                sql = GeneratorSqlBuild.getSqlByKey(KEY_DELETEUSERBYIDS);
            }
            StringBuilder buff = new StringBuilder(sql);
            buff.append(" (");
            buff.append(BasicSqlBuild.convertArrayToSqlString(ids));
            buff.append(")");
            int i = jdbcTemplate.update(buff.toString());
            return i == ids.length;
        } catch (Exception e) {
            Global.getInstance().logError("deleteUserByIds error", e);
            throw new QueryException(e);
        }
    }
}