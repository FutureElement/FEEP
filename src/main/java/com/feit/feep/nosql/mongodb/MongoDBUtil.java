package com.feit.feep.nosql.mongodb;

import com.feit.feep.nosql.entity.NoSqlDBConfig;
import com.feit.feep.core.Global;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

/**
 * mongdb util
 * Created by zhanggang on 2015/7/9.
 */
public class MongoDBUtil {

    public static MongoTemplate getMongoTemplate() throws UnknownHostException {
        NoSqlDBConfig mongoDBConfig = Global.getInstance().getFeepConfig().getNoSqlDBConfig();
        MongoClient mongoClient = new MongoClient(mongoDBConfig.getIp(), mongoDBConfig.getPort());
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, mongoDBConfig.getDbName());
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        return mongoTemplate;
    }
}
