package com.feit.feep.system.login;

import com.feit.feep.exception.FException;

import java.io.Serializable;

public interface ILoginManager extends Serializable {

    /**
     * 用户登陆
     * 
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    boolean feep_login(String username, String password) throws FException;

    /**
     * 用户登出
     * 
     * @return
     */
    boolean feep_logout();

    /**
     * 修改密码，刷新session
     * 
     * @param id
     * @param password
     * @return
     */
    boolean feep_modifyLoginUserPassword(String id, String password);
}
