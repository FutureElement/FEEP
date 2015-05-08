package com.feit.feep.cache.ehcache;

import java.util.LinkedList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.exception.FException;
import com.feit.feep.util.FeepUtil;

public class EhcacheManager implements FeepCacheManager {

    private CacheManager cacheManager;
    private CachePool    sampleCache;

    public EhcacheManager(String configPath) {
        sampleCache = CachePool.SAMPLECACHE;
        cacheManager = CacheManager.newInstance(FeepUtil.getClassPathURL(configPath));
    }

    @Override
    public String[] getKeys() {
        return getKeys(sampleCache);
    }

    @Override
    public String[] getKeys(CachePool cachePool) {
        @SuppressWarnings("unchecked")
        List<String> keys = cacheManager.getCache(cachePool.getCacheName()).getKeys();
        if (null != keys && keys.size() > 0) {
            return keys.toArray(new String[keys.size()]);
        }
        return new String[0];
    }

    @Override
    public <T> List<T> getAll(Class<T> classType) throws FException {
        return getAll(sampleCache, classType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(CachePool cachePool, Class<T> classType) throws FException {
        List<T> list = new LinkedList<T>();
        String[] keys = getKeys(cachePool);
        if (null != keys && keys.length > 0) {
            for (String key : keys) {
                list.add((T) get(cachePool, key));
            }
        }
        return list;
    }

    @Override
    public Object get(String key) {
        return get(sampleCache, key);
    }

    @Override
    public Object get(CachePool cachePool, String key) {
        Element element = cacheManager.getCache(cachePool.getCacheName()).get(key);
        if (null != element) {
            return element.getObjectValue();
        }
        return null;
    }

    @Override
    public void put(String key, Object value) {
        put(sampleCache, key, value);
    }

    @Override
    public void put(CachePool cachePool, String key, Object value) {
        Element element = new Element(key, value);
        cacheManager.getCache(cachePool.getCacheName()).put(element);
    }

    @Override
    public void update(String key, Object value) {
        update(sampleCache, key, value);
    }

    @Override
    public void update(CachePool cachePool, String key, Object value) {
        Element element = new Element(key, value);
        cacheManager.getCache(cachePool.getCacheName()).replace(element);
    }

    @Override
    public void remove(String key) {
        remove(sampleCache, key);
    }

    @Override
    public void remove(CachePool cachePool, String key) {
        cacheManager.getCache(cachePool.getCacheName()).remove(key);
    }

    @Override
    public void removeAll() {
        removeAll(sampleCache);
    }

    @Override
    public void removeAll(CachePool cachePool) {
        cacheManager.getCache(cachePool.getCacheName()).removeAll();
    }

    @Override
    public int getSize() {
        return getSize(sampleCache);
    }

    @Override
    public int getSize(CachePool cachePool) {
        return cacheManager.getCache(cachePool.getCacheName()).getKeys().size();
    }

    @Override
    public void close() {
        cacheManager.shutdown();
    }

    @Override
    public <T> List<T> findByAttribute(String attributeName, Object value, Class<T> classType) throws FException {
        return findByAttribute(sampleCache, attributeName, value, classType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findByAttribute(CachePool cachePool, String attributeName, Object value, Class<T> classType) throws FException {
        List<T> rs = new LinkedList<T>();
        Cache cache = cacheManager.getCache(cachePool.getCacheName());
        Attribute<Object> attribute = cache.getSearchAttribute(attributeName);
        Query query = cache.createQuery();
        query.includeKeys();
        query.includeValues();
        query.addCriteria(attribute.eq(value));
        Results results = query.execute();
        if (null != results && results.size() > 0) {
            List<Result> resultList = results.all();
            for (int i = 0; i < resultList.size(); i++) {
                rs.add((T) resultList.get(i).getValue());
            }
        }
        return rs;
    }

    @Override
    public void addCache(Cache cache) {
        cacheManager.addCache(cache);
    }

}
