package com.feit.feep.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.exception.FException;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.service.IUserService;
import com.feit.feep.system.user.BasicUserDao;
import com.feit.feep.util.FeepUtil;

@Service
public class UserService implements IUserService {

    @Autowired
    private BasicUserDao        basicUserDao;

    private static final String ADMIN_NAME = "feep";

    @Override
    public boolean isAdmin(String username) {
        return ADMIN_NAME.equals(username);
    }

    @Override
    public boolean initUserToCache() throws FException {
        List<FeepUser> users = basicUserDao.getAllUsers();
        return addUsersToCache(users);
    }

    @Override
    public boolean addUserToCache(FeepUser user) throws FException {
        FeepCacheManager cm = Global.getInstance().getCacheManager();
        cm.put(CachePool.USERCACHE, user.getId(), user);
        return true;
    }

    @Override
    public boolean addUsersToCache(List<FeepUser> users) {
        FeepCacheManager cm = Global.getInstance().getCacheManager();
        if (null != users && users.size() > 0) {
            for (FeepUser user : users) {
                cm.put(CachePool.USERCACHE, user.getId(), user);
            }
        }
        return true;
    }

    @Override
    public FeepUser getUserById(String id) throws FException {
        if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
            return (FeepUser) Global.getInstance().getCacheManager().get(CachePool.USERCACHE, id);
        }
        return basicUserDao.getUserById(id);
    }

    @Override
    public FeepUser getUserByUserName(String username) throws FException {
        if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
            List<FeepUser> rs = Global.getInstance()
                                      .getCacheManager()
                                      .findByAttribute(CachePool.USERCACHE, "username", username, FeepUser.class);
            if (null != rs) {
                return rs.get(0);
            }
        } else {
            return basicUserDao.getUserByUserName(username);
        }
        return null;
    }

    @Override
    public boolean insertUser(FeepUser user) throws FException {
        if (FeepUtil.isNull(user.getId())) {
            user.setId(FeepUtil.getUUID());
        }
        if (basicUserDao.insertUser(user)) {
            if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
                return addUserToCache(user);
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertUsers(List<FeepUser> users) throws FException {
        if (null != users) {
            for (FeepUser user : users) {
                if (FeepUtil.isNull(user.getId())) {
                    user.setId(FeepUtil.getUUID());
                }
            }
        }
        if (basicUserDao.insertUsers(users)) {
            if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
                return addUsersToCache(users);
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(FeepUser user) throws FException {
        if (basicUserDao.updateUser(user)) {
            if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
                Global.getInstance().getCacheManager().update(CachePool.USERCACHE, user.getId(), user);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(String id, boolean isRealDelete) throws FException {
        if (basicUserDao.deleteUserById(id, isRealDelete)) {
            if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
                Global.getInstance().getCacheManager().remove(CachePool.USERCACHE, id);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserByIds(String[] ids, boolean isRealDelete) throws FException {
        if (basicUserDao.deleteUserByIds(ids, isRealDelete)) {
            if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
                for (String id : ids) {
                    Global.getInstance().getCacheManager().remove(CachePool.USERCACHE, id);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean insertOrUpdateUser(FeepUser user) throws FException {
        if (null != user) {
            if (FeepUtil.isNull(user.getId())) {
                return insertUser(user);
            } else {
                FeepUser dbuser = getUserById(user.getId());
                if (null == dbuser) {
                    return insertUser(user);
                } else {
                    return updateUser(user);
                }
            }
        }
        return false;
    }

    @Override
    public boolean selectUser(FeepQueryBean qbean) throws FException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAdminToCache() throws FException {
        FeepUser admin = basicUserDao.getUserByUserName(ADMIN_NAME);
        if (null != admin) {
            return addUserToCache(admin);
        }
        return false;
    }

    @Override
    public List<FeepUser> getAllUsers() throws FException {
        if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
            return Global.getInstance().getCacheManager().getAll(CachePool.USERCACHE, FeepUser.class);
        } else {
            return basicUserDao.getAllUsers();
        }
    }
}
