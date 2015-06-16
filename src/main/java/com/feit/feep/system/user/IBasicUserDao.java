package com.feit.feep.system.user;

import java.util.List;

import com.feit.feep.exception.FException;
import com.feit.feep.exception.dbms.QueryException;
import com.feit.feep.system.entity.FeepUser;

public interface IBasicUserDao {

    /**
     * 根据id获得用户信息
     * 
     * @param userid
     * @return
     */
    FeepUser getUserById(String userid) throws QueryException;

    /**
     * 根据登陆名获得用户信息
     * 
     * @param username
     * @return
     */
    FeepUser getUserByUserName(String username) throws QueryException;

    /**
     * 获取所有用户
     * 
     * @return
     * @throws QueryException
     */
    List<FeepUser> getAllUsers() throws QueryException;

    /**
     * 新增用户
     * 
     * @param user
     * @return
     * @throws FException
     */
    String insertUser(FeepUser user) throws QueryException;

    /**
     * 批量添加用户
     * 
     * @param user
     * @return
     * @throws FException
     */
    String[] insertUsers(List<FeepUser> user) throws QueryException;

    /**
     * 修改用户
     * 
     * @param user
     * @return
     * @throws FException
     */
    boolean updateUser(FeepUser user) throws QueryException;

    /**
     * 删除用户
     * 
     * @param id
     * @param isRealDelete
     * @return
     * @throws FException
     */
    boolean deleteUserById(String id, boolean isRealDelete) throws QueryException;

    /**
     * 删除用户
     * 
     * @param ids
     * @param isRealDelete
     * @return
     * @throws FException
     */
    boolean deleteUserByIds(String[] ids, boolean isRealDelete) throws QueryException;

}
