package com.feit.feeptest.system.user;

import com.feit.feep.system.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.exception.FException;
import com.feit.feep.system.entity.FeepUser;

public class TestUserService extends FeepJUnit {
    @Autowired
    private IUserService userService;

    @Test
    public void test1() throws FException {
        FeepUser user = userService.getUserById("20150527001");
        String username = user.getUsername();
        Global.getInstance().logInfo(username, this.getClass());
        FeepUser user2 = userService.getUserByUserName(user.getUsername());
        Global.getInstance().logInfo(user2.getShowname(), this.getClass());
    }

}
