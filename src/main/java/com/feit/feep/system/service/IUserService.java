package com.feit.feep.system.service;

import java.util.List;

import com.feit.feep.exception.FException;
import com.feit.feep.system.entity.FeepUser;

public interface IUserService {

    /**
     * 是否是管理员
     *
     * @param username
     * @return
     */
    boolean isAdmin(String username);

    /**
     * 初始化用户到缓存
     *
     * @return
     */
    boolean initUserToCache() throws FException;

    /**
     * 添加用户到缓存
     *
     * @param user
     * @return
     */
    boolean addUserToCache(FeepUser user) throws FException;

    /**
     * 批量添加用户到缓存
     *
     * @param users
     * @return
     */
    boolean addUsersToCache(List<FeepUser> users) throws FException;

    /**
     * 根据id获得用户信息
     *
     * @param id
     * @return
     */
    FeepUser getUserById(String id) throws FException;

    /**
     * 根据登陆名获得用户信息
     *
     * @param username
     * @return
     */
    FeepUser getUserByUserName(String username) throws FException;

    /**
     * 获得全部用户
     *
     * @return
     * @throws FException
     */
    List<FeepUser> getAllUsers() throws FException;

    /**
     * 新增用户
     *
     * @param user
     * @return
     * @throws FException
     */
    String insertUser(FeepUser user) throws FException;

    /**
     * 批量添加用户
     *
     * @param users
     * @return
     * @throws FException
     */
    String[] insertUsers(List<FeepUser> users) throws FException;

    /**
     * 修改用户
     *
     * @param user
     * @return
     * @throws FException
     */
    boolean updateUser(FeepUser user) throws FException;

    /**
     * 删除用户
     *
     * @param id
     * @param isRealDelete
     * @return
     * @throws FException
     */
    boolean deleteUserById(String id, boolean isRealDelete) throws FException;

    /**
     * 批量删除用户
     *
     * @param ids
     * @param isRealDelete
     * @return
     * @throws FException
     */
    boolean deleteUserByIds(String[] ids, boolean isRealDelete) throws FException;

    /**
     * 新增或者修改用户
     *
     * @param user
     * @return
     * @throws FException
     */
    String insertOrUpdateUser(FeepUser user) throws FException;

    /**
     * 增加管理员信息到缓存
     *
     * @return
     * @throws FException
     */
    boolean addAdminToCache() throws FException;
}
