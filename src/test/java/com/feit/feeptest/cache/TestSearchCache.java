package com.feit.feeptest.cache;

import java.util.List;

import com.feit.feep.system.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.exception.FException;
import com.feit.feep.system.entity.FeepUser;
import com.feit.feep.system.service.impl.UserService;

public class TestSearchCache extends FeepJUnit {

    @Autowired
    private IUserService userService;

    @Test
    public void test() throws FException {
        List<FeepUser> list = userService.getAllUsers();
        if (null != list) {
            for (FeepUser user : list) {
                List<FeepUser> rs = Global.getInstance()
                                          .getCacheManager()
                                          .findByAttribute(CachePool.USERCACHE, "username", user.getUsername(), FeepUser.class);
                Global.getInstance().logInfo(rs.get(0).getId());
                Global.getInstance().logInfo(rs.get(0).getShowname());
            }
        }
    }

}
