package com.feit.feep.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;

public class CacheFactory {

    private CacheFactory(){

    }

    public static Cache create(String cacheName) {
        return create(cacheName, null);
    }

    public static Cache create(String cacheName, String[] searchNames) {
        CacheConfiguration cacheConfig = new CacheConfiguration();
        cacheConfig.name(cacheName).maxEntriesLocalHeap(10000)
				.maxEntriesLocalDisk(2000000).memoryStoreEvictionPolicy(
						MemoryStoreEvictionPolicy.LFU)
				.diskSpoolBufferSizeMB(30);
        if (null != searchNames && searchNames.length > 0) {
            // 新建一个Searchable对象
            Searchable searchable = new Searchable();
            for (String attributeName : searchNames) {
                // 新建一个查询属性
                SearchAttribute searchAttribute = new SearchAttribute();
                // 指定查询属性的名称和属性提取器的类名
                searchAttribute.setName(attributeName);
                // Searchalbe对象添加查询属性
                searchable.addSearchAttribute(searchAttribute);
                // 给Cache配置Searchable对象，表明该Cache是一个可查询的Cache
            }
            cacheConfig.addSearchable(searchable);
        }
        // 使用CacheConfig创建Cache对象
        return new Cache(cacheConfig);
    }
}
