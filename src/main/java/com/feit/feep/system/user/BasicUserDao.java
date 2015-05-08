package com.feit.feep.system.user;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.build.FeepEntityRowMapper;
import com.feit.feep.exception.dbms.QueryException;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.util.FeepUtil;

/**
 * 用户管理，为用户登陆，数据初始化提供支持 ，使用原生JDBC SQL 防止数据出错无法登陆
 * 
 * @author ZhangGang
 *
 */
@Repository
public class BasicUserDao implements IBasicUserDao {

    @Autowired
    private JdbcTemplate        jdbcTemplate;

    private static final String KEY_SELECTALL           = "sql.common.user.selectAll";
    private static final String KEY_FINDBYID            = "sql.common.user.findById";
    private static final String KEY_FINDBYUSERNAME      = "sql.common.user.findByUserName";
    private static final String KEY_INSERTUSER          = "sql.common.user.insertUser";
    private static final String KEY_UPDATEUSER          = "sql.common.user.updateUser";

    private static final String KEY_DELETEUSERBYID      = "sql.common.user.deleteUserById";
    private static final String KEY_DELETEUSERBYIDS     = "sql.common.user.deleteUserByIds";
    private static final String KEY_REALDELETEUSERBYID  = "sql.common.user.realDeleteUserById";
    private static final String KEY_REALDELETEUSERBYIDS = "sql.common.user.realDeleteUserByIds";

    private FeepUser getUser(String sql, String parameter) throws QueryException {
        try{
            List<FeepUser> list = jdbcTemplate.query(sql, new Object[]{parameter}, FeepEntityRowMapper.getMapper(FeepUser.class));
            if (null != list && !list.isEmpty()) {
                return list.get(0);
            }
            return null;
        }catch(Exception e){
            Global.getInstance().logError("BasicUserDao getUser error,sql : " + sql + " , parameter : " + parameter, e);
            throw new QueryException(e);
        }
    }

    @Override
    public FeepUser getUserById(String userid) throws QueryException {
        try {
            String sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_FINDBYID);
            return getUser(sql, userid);
        }catch (Exception e) {
            Global.getInstance().logError("find user by id error,id:" + userid, e);
            throw new QueryException(e);
        }
    }

    @Override
    public FeepUser getUserByUserName(String username) throws QueryException {
        try {
            String sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_FINDBYUSERNAME);
            return getUser(sql, username);
        }catch (Exception e) {
            Global.getInstance().logError("find user by username error,username:" + username, e);
            throw new QueryException(e);
        }
    }

    @Override
    public List<FeepUser> getAllUsers() throws QueryException {
        List<FeepUser> list = null;
        try {
            String sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_SELECTALL);
            list = jdbcTemplate.query(sql, FeepEntityRowMapper.getMapper(FeepUser.class));
        }catch (Exception e) {
            Global.getInstance().logError("select all users error", e);
            throw new QueryException(e);
        }
        return list;
    }

    @Override
    public boolean insertUser(FeepUser user) throws QueryException {
        try {
            String sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_INSERTUSER);
            int i = jdbcTemplate.update(sql, convertUserToParameter(user));
            if (i == 1) {
                return true;
            }
        }catch (Exception e) {
            Global.getInstance().logError("insertUser error", e);
            throw new QueryException(e);
        }
        return false;
    }

    private Object[] convertUserToParameter(FeepUser user) {
        return new Object[]{user.getId(),
                user.getUsername(),
                user.getShowname(),
                user.getPassword(),
                user.getSort(),
                user.getType(),
                user.getPhoto(),
                user.getStudentcard(),
                user.getIdentitycard(),
                user.getBirthday(),
                user.getTel(),
                user.getEmail(),
                user.getAddress(),
                user.getRemarks()};
    }

    @Override
    public boolean insertUsers(List<FeepUser> users) throws QueryException {
        try {
            List<Object[]> batchArgs = null;
            if (null != users && !users.isEmpty()) {
                batchArgs = new LinkedList<Object[]>();
                for (FeepUser user : users) {
                    batchArgs.add(convertUserToParameter(user));
                }
            }
            String sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_INSERTUSER);
            jdbcTemplate.batchUpdate(sql, batchArgs);
            return true;
        }catch (Exception e) {
            Global.getInstance().logError("insert batch Users error", e);
            throw new QueryException(e);
        }
    }

    @Override
    public boolean updateUser(FeepUser user) throws QueryException {
        try {
            String sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_UPDATEUSER);
            StringBuilder buff = new StringBuilder();
            List<Object> argList = new LinkedList<Object>();
            buff.append(sql);
            if (null != user && FeepUtil.isNull(user.getId())) {
                if (FeepUtil.isNull(user.getShowname())) {
                    buff.append(" showname=?,");
                    argList.add(user.getShowname());
                }
                if (FeepUtil.isNull(user.getPassword())) {
                    buff.append(" password=?,");
                    argList.add(user.getPassword());
                }
                if (FeepUtil.isNull(user.getSort())) {
                    buff.append(" sort=?,");
                    argList.add(user.getSort());
                }
                if (FeepUtil.isNull(user.getType())) {
                    buff.append(" type=?,");
                    argList.add(user.getType());
                }
                if (FeepUtil.isNull(user.getPhoto())) {
                    buff.append(" photo=?,");
                    argList.add(user.getPhoto());
                }
                if (FeepUtil.isNull(user.getStudentcard())) {
                    buff.append(" studentcard=?,");
                    argList.add(user.getStudentcard());
                }
                if (FeepUtil.isNull(user.getIdentitycard())) {
                    buff.append(" identitycard=?,");
                    argList.add(user.getIdentitycard());
                }
                if (null != user.getBirthday()) {
                    buff.append(" birthday=?,");
                    argList.add(user.getBirthday());
                }
                if (FeepUtil.isNull(user.getTel())) {
                    buff.append(" tel=?,");
                    argList.add(user.getTel());
                }
                if (FeepUtil.isNull(user.getEmail())) {
                    buff.append(" email=?,");
                    argList.add(user.getEmail());
                }
                if (FeepUtil.isNull(user.getAddress())) {
                    buff.append(" address=?,");
                    argList.add(user.getAddress());
                }
                if (FeepUtil.isNull(user.getRemarks())) {
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
        }catch (Exception e) {
            Global.getInstance().logError("updateUser error", e);
            throw new QueryException(e);
        }
    }

    @Override
    public boolean deleteUserById(String id, boolean isRealDelete) throws QueryException {
        try {
            String sql = null;
            if (isRealDelete) {
                sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_REALDELETEUSERBYID);
            } else {
                sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_DELETEUSERBYID);
            }
            int i = jdbcTemplate.update(sql, new Object[]{id});
            if (i == 1) {
                return true;
            }
            return false;
        }catch (Exception e) {
            Global.getInstance().logError("deleteUserById error", e);
            throw new QueryException(e);
        }
    }

    @Override
    public boolean deleteUserByIds(String[] ids, boolean isRealDelete) throws QueryException {
        try {
            if (null == ids || ids.length == 0){
                return false;
            }
            String sql = null;
            if (isRealDelete) {
                sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_REALDELETEUSERBYIDS);
            } else {
                sql = (String) Global.getInstance().getCacheManager().get(CachePool.SQLCACHE, KEY_DELETEUSERBYIDS);
            }
            StringBuilder buff = new StringBuilder(sql);
            buff.append(" (");
            for (int i = 0; i < ids.length; i++) {
                buff.append("'");
                buff.append(ids[i]);
                buff.append("'");
                if (i != (ids.length - 1)) {
                    buff.append(",");
                }
            }
            buff.append(")");
            int i = jdbcTemplate.update(buff.toString());
            if (i == ids.length) {
                return true;
            }
            return false;
        }catch (Exception e) {
            Global.getInstance().logError("deleteUserByIds error", e);
            throw new QueryException(e);
        }
    }
}