package com.feit.feep.cache.ehcache;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.feit.feep.dbms.build.GeneratorSqlBuild;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.dbms.entity.query.SortField;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.TransactionController;
import net.sf.ehcache.search.*;

import com.feit.feep.cache.FeepCacheManager;
import com.feit.feep.exception.FException;
import com.feit.feep.util.FeepUtil;

public class EhcacheManager implements FeepCacheManager {

    private CacheManager cacheManager;
    private CachePool sampleCache;

    public EhcacheManager(String configPath) {
        sampleCache = CachePool.SAMPLECACHE;
        cacheManager = CacheManager.newInstance(FeepUtil.getClassPathURL(configPath));
    }

    public TransactionController getTransaction() {
        return cacheManager.getTransactionController();
    }

    @Override
    public String[] getKeys() {
        return getKeys(sampleCache);
    }

    @Override
    public String[] getKeys(CachePool cachePool) {
        @SuppressWarnings("unchecked")
        List<String> keys = getCache(cachePool).getKeys();
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
    public Object get(final String key) {
        return get(sampleCache, key);
    }

    @Override
    public Object get(CachePool cachePool, final String key) {
        Element element = getCache(cachePool).get(key);
        if (null != element) {
            return element.getObjectValue();
        }
        return null;
    }

    @Override
    public void put(final String key, final Object value) {
        put(sampleCache, key, value);
    }

    @Override
    public void put(CachePool cachePool, final String key, final Object value) {
        Element element = new Element(key, (Serializable) value);
        getCache(cachePool).put(element);
    }

    @Override
    public void update(final String key, final Object value) {
        update(sampleCache, key, value);
    }

    @Override
    public void update(CachePool cachePool, final String key, final Object value) {
        Element element = new Element(key, (Serializable) value);
        getCache(cachePool).replace(element);
    }

    @Override
    public void remove(final String key) {
        remove(sampleCache, key);
    }

    @Override
    public void remove(CachePool cachePool, final String key) {
        getCache(cachePool).remove(key);
    }

    @Override
    public void removeAll(final String[] keys) {
        removeAll(sampleCache, keys);
    }

    @Override
    public void removeAll(CachePool cachePool, final String[] keys) {
        if (!FeepUtil.isNull(keys)) {
            List<String> keysCollection = new LinkedList<String>();
            Collections.addAll(keysCollection, keys);
            getCache(cachePool).removeAll(keysCollection);
        }
    }

    @Override
    public void removeAll() {
        removeAll(sampleCache);
    }

    @Override
    public void removeAll(CachePool cachePool) {
        getCache(cachePool).removeAll();
    }

    @Override
    public int getSize() {
        return getSize(sampleCache);
    }

    @Override
    public int getSize(CachePool cachePool) {
        return getCache(cachePool).getKeys().size();
    }

    @Override
    public void close() {
        cacheManager.shutdown();
    }

    @Override
    public <T> List<T> findByAttribute(final String attributeName, final Object value, Class<T> classType) throws FException {
        return findByAttribute(sampleCache, attributeName, value, classType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findByAttribute(CachePool cachePool, final String attributeName, final Object value, Class<T> classType) throws FException {
        List<T> rs = new LinkedList<T>();
        Cache cache = getCache(cachePool);
        Attribute<Object> attribute = cache.getSearchAttribute(attributeName);
        Query query = cache.createQuery();
        query.includeKeys();
        query.includeValues();
        query.addCriteria(attribute.eq(value));
        Results results = query.execute();
        if (null != results && results.size() > 0) {
            List<Result> resultList = results.all();
            for (Result aResultList : resultList) {
                rs.add((T) aResultList.getValue());
            }
        }
        if (null != results) {
            results.discard();
        }
        return rs;
    }

    @Override
    public <T> List<T> queryCache(final FeepQueryBean queryBean, Class<T> classType) throws FException {
        return queryCache(sampleCache, queryBean, classType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> queryCache(CachePool cachePool, final FeepQueryBean queryBean, Class<T> classType) throws FException {
        List<T> rs = new LinkedList<T>();
        Cache cache = getCache(cachePool);
        Query query = cache.createQuery();
        query.includeKeys();
        query.includeValues();
        List<QueryParameter> queryParameters = queryBean.getQueryParameters();
        if (!FeepUtil.isNull(queryParameters)) {
            for (QueryParameter queryParameter : queryParameters) {
                String value = queryParameter.getParameterValue();
                Attribute<Object> attribute = cache.getSearchAttribute(queryParameter.getFieldName());
                switch (queryParameter.getCondition()) {
                    case LIKE:
                        query.addCriteria(attribute.ilike("*" + value + "*"));
                        break;
                    case LEFTLIKE:
                        query.addCriteria(attribute.ilike("*" + value));
                        break;
                    case RIGHTLIKE:
                        query.addCriteria(attribute.ilike(value + "*"));
                        break;
                    case NOTLIKE:
                        query.addCriteria(attribute.notIlike("*" + value + "*"));
                        break;
                    case EQUALS:
                        query.addCriteria(attribute.eq(value));
                        break;
                    case NOTEQUALS:
                        query.addCriteria(attribute.ne(value));
                        break;
                    case LT:
                        query.addCriteria(attribute.lt(value));
                        break;
                    case LTE:
                        query.addCriteria(attribute.le(value));
                        break;
                    case GT:
                        query.addCriteria(attribute.gt(value));
                        break;
                    case GTE:
                        query.addCriteria(attribute.ge(value));
                        break;
                    default:
                        query.addCriteria(attribute.ilike("*" + value + "*"));
                        break;
                }
            }
        }
        List<SortField> sortFields = queryBean.getSortFields();
        if (!FeepUtil.isNull(sortFields)) {
            for (SortField sortField : sortFields) {
                Attribute<Object> attribute = cache.getSearchAttribute(sortField.getFieldName());
                query.addOrderBy(attribute, sortField.isAsc() ? Direction.ASCENDING : Direction.DESCENDING);
            }
        }
        boolean isPager = queryBean.getPageSize() > 0;
        if (isPager) {
            query.maxResults((queryBean.getPageIndex() + 1) * queryBean.getPageSize());
        }
        Results results = query.execute();
        if (null != results && results.size() > 0) {
            List<Result> resultList;
            if (isPager) {
                int range[] = GeneratorSqlBuild.getPageStartAndEnd(queryBean.getPageIndex(), queryBean.getPageSize());
                resultList = results.range(range[0], range[1]);
            } else {
                resultList = results.all();
            }
            for (Result aResultList : resultList) {
                rs.add((T) aResultList.getValue());
            }
        }
        if (null != results) {
            results.discard();
        }
        return rs;
    }

    @Override
    public void addCache(Cache cache) {
        cacheManager.addCache(cache);
    }

    private Cache getCache(CachePool cachePool) {
        return cacheManager.getCache(cachePool.getCacheName());
    }
}
