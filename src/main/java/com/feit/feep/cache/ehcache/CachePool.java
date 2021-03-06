package com.feit.feep.cache.ehcache;

public enum CachePool {

    SAMPLECACHE("SampleCache"),
    USERCACHE("UserCache"),
    SQLCACHE("SQLCache"),
    ORGCACHE("OrgCache"),
    ROLECACHE("RoleCache"),
    RESOURCECACHE("ResourceCache"),
    FEEPDATASOURCE("FeepDataSource"),
    TABLECACHE("TableCache"),
    TABLEFIELDCACHE("TableFieldCache"),
    MODULECACHE("ModuleCache"),
    MODULEFIELDCACHE("ModuleFieldCache"),
    TABLEMODULERELATIONCACHE("TableModuleRelationCache"),
    TABLEFIELDRELATIONCACHE("TableFieldRelationCache"),
    DICTIONARYCACHE("DictionaryCache"),
    DICTIONARYITEMCACHE("DictionaryItemCache");

    private String cacheName;

    CachePool(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return cacheName;
    }
}
