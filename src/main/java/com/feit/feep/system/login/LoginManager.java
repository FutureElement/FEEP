package com.feit.feep.system.login;

import javax.servlet.http.HttpServletRequest;

import com.feit.feep.exception.FException;
import org.springframework.beans.factory.annotation.Autowired;

import com.feit.feep.core.Global;
import com.feit.feep.core.annotation.FeepController;
import com.feit.feep.exception.json.JsonException;
import com.feit.feep.mvc.entity.FeepMvcKey;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.service.impl.UserService;
import com.feit.feep.util.FeepUtil;
import com.feit.feep.util.json.FeepJsonUtil;
import com.feit.feep.util.security.MD5Util;

@FeepController
public class LoginManager implements ILoginManager {

    private static final long serialVersionUID = -7814617231717639507L;

    @Autowired
    private UserService       userService;

    @Override
    public boolean feep_login(String username, String password) throws FException {
        try {
            FeepUser dbuser = userService.getUserByUserName(username);
            if (null != dbuser) {
                if (dbuser.getPassword().equals(MD5Util.encryption(password))) {
                    FeepUser user = new FeepUser();
                    user.setId(dbuser.getId());
                    user.setPassword(dbuser.getPassword());
                    addUserToSession(user);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new FException(e);
        }
    }

    private void addUserToSession(FeepUser user) throws JsonException {
        HttpServletRequest request = Global.getInstance().getRequest();
        request.getSession().removeAttribute(FeepMvcKey.KEY_SESSION_USER);
        String userStr = FeepUtil.simpleCryption(FeepJsonUtil.toJson(user), FeepMvcKey.CRYPTION_PUBLIC_KEY);
        request.getSession().setAttribute(FeepMvcKey.KEY_SESSION_USER, userStr);
    }

    @Override
    public boolean feep_logout() {
        Global.getInstance().getRequest().getSession().removeAttribute(FeepMvcKey.KEY_SESSION_USER);
        return true;
    }

    @Override
    public boolean feep_modifyLoginUserPassword(String id, String password) {
        try {
            FeepUser user = new FeepUser();
            user.setId(id);
            user.setPassword(MD5Util.encryption(password));
            if (userService.updateUser(user)) {
                addUserToSession(user);
                return true;
            }
        } catch (Exception e) {
            Global.getInstance().logError("modifyLoginUserPassword error", e);
        }
        return false;
    }
}