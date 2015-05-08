package com.feit.feeptest.system.user;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.exception.FException;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.user.BasicUserDao;
import com.feit.feep.util.json.FeepJsonUtil;

public class TestBasicUser extends FeepJUnit{

    @Autowired
    private BasicUserDao basicUserDao;

    @Test
    public void test1() throws FException {
        FeepUser user1 = basicUserDao.getUserById("1000");
        FeepUser user2 = basicUserDao.getUserByUserName("zhanggang");
        List<FeepUser> list = basicUserDao.getAllUsers();
        Global.getInstance().logInfo(FeepJsonUtil.toJson(user1));
        Global.getInstance().logInfo(FeepJsonUtil.toJson(user2));
        Global.getInstance().logInfo(list.get(0).getShowname());
    }
}
