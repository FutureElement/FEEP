package com.feit.feeptest.system.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.exception.FException;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.service.impl.UserService;

public class TestUserService extends FeepJUnit {
    @Autowired
    private UserService userService;

    @Test
    public void test1() throws FException {
        FeepUser user = userService.getUserById("1000");
        String username = user.getUsername();
        Global.getInstance().logInfo(username, this.getClass());
        FeepUser user2 = userService.getUserByUserName(user.getUsername());
        Global.getInstance().logInfo(user2.getShowname(), this.getClass());
    }

}
