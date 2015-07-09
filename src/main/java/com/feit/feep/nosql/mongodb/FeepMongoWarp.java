package com.feit.feep.nosql.mongodb;

import com.feit.feep.nosql.FeepNoSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by zhanggang on 2015/7/9.
 */
@Repository
public class FeepMongoWarp implements FeepNoSql {

    @Autowired
    private MongoTemplate mongoTemplate;



}
