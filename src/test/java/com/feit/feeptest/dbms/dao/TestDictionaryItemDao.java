package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.dbms.dao.IFeepDictionaryItemDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ZhangGang on 2015/6/30 0030.
 */
public class TestDictionaryItemDao extends FeepJUnit {

    @Autowired
    private IFeepDictionaryItemDao dictionaryItemDao;
}
