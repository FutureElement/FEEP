package com.feit.feep.system.service.impl;

import java.util.List;

import com.feit.feep.system.user.IBasicUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.core.Global;
import com.feit.feep.exception.FException;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.service.IUserService;
import com.feit.feep.util.FeepUtil;

@Service
public class UserService implements IUserService {

    @Autowired
    private IBasicUserDao basicUserDao;

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
                    .findByAttribute(CachePool.USERCACHE, FeepUser.USERNAME, username, FeepUser.class);
            if (!FeepUtil.isNull(rs)) {
                return rs.get(0);
            }
        } else {
            return basicUserDao.getUserByUserName(username);
        }
        return null;
    }

    @Override
    public String insertUser(FeepUser user) throws FException {
        if (FeepUtil.isNull(user.getId())) {
            user.setId(FeepUtil.getUUID());
        }
        String id = basicUserDao.insertUser(user);
        if (!FeepUtil.isNull(id)) {
            if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
                addUserToCache(user);
            }
        }
        return id;
    }

    @Override
    public String[] insertUsers(List<FeepUser> users) throws FException {
        if (null != users) {
            for (FeepUser user : users) {
                if (FeepUtil.isNull(user.getId())) {
                    user.setId(FeepUtil.getUUID());
                }
            }
        }
        String[] ids = basicUserDao.insertUsers(users);
        if (!FeepUtil.isNull(ids)) {
            if (Global.getInstance().getFeepConfig().isAddUserToCache()) {
                addUsersToCache(users);
            }
        }
        return ids;
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
    public String insertOrUpdateUser(FeepUser user) throws FException {
        if (null != user) {
            if (FeepUtil.isNull(user.getId())) {
                return insertUser(user);
            } else {
                FeepUser dbuser = getUserById(user.getId());
                if (null == dbuser) {
                    return insertUser(user);
                } else {
                    updateUser(user);
                    return user.getId();
                }
            }
        }
        return null;
    }

    @Override
    public boolean addAdminToCache() throws FException {
        FeepUser admin = basicUserDao.getUserByUserName(ADMIN_NAME);
        return null != admin && addUserToCache(admin);
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
