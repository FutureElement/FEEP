package com.feit.feeptest.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;

public class TestCache extends FeepJUnit {
    @Test
    public void test1(){
        FeepCacheManager fcm = Global.getInstance().getCacheManager();
        int size = fcm.getSize();
        Global.getInstance().logInfo(size);
        for (int i = 0; i < 10000; i++) {
            fcm.put("test" + i, "1211111111113" + i);
        }
        int size2 = fcm.getSize();
        Global.getInstance().logInfo(size2);
        String value = (String) fcm.get("test1005");
        Global.getInstance().logInfo(value);
        Assert.assertEquals("12111111111131005", value);
    }

    @Ignore
    public void test2() {
        CacheManager manager = CacheManager.getInstance();
        manager.addCache(new Cache("test", 100, true, false, 600, 300));
        Cache cache = manager.getCache("test");
        int a1 = cache.getSize();
        Global.getInstance().logInfo(a1);
        cache.put(new Element("test1", 123123));
        int a2 = cache.getSize();
        Global.getInstance().logInfo(a2);
        manager.shutdown();
    }
}