package com.feit.feep.cache;

import java.util.List;

import com.feit.feep.dbms.entity.query.FeepQueryBean;
import net.sf.ehcache.Cache;

import com.feit.feep.cache.ehcache.CachePool;
import com.feit.feep.exception.FException;

public interface FeepCacheManager {

    String[] getKeys();

    String[] getKeys(CachePool cachePool);

    <T> List<T> getAll(Class<T> classType) throws FException;

    <T> List<T> getAll(CachePool cachePool, Class<T> classType) throws FException;

    Object get(String key);

    Object get(CachePool cachePool, String key);

    <T> List<T> findByAttribute(String attributeName, Object value, Class<T> classType) throws FException;

    <T> List<T> findByAttribute(CachePool cachePool, String attributeName, Object value, Class<T> classType) throws FException;

    <T> List<T> queryCache(FeepQueryBean queryBean, Class<T> classType) throws FException;

    <T> List<T> queryCache(CachePool cachePool, FeepQueryBean queryBean, Class<T> classType) throws FException;

    void put(String key, Object value);

    void put(CachePool cachePool, String key, Object value);

    void remove(String key);

    void remove(CachePool cachePool, String key);

    void update(String key, Object value);

    void update(CachePool cachePool, String key, Object value);

    void removeAll();

    void removeAll(CachePool cachePool);

    int getSize();

    int getSize(CachePool cachePool);

    void close();

    void addCache(Cache cache);
}
