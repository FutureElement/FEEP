package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.dao.IFeepDictionaryDao;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.query.Condition;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.entity.query.QueryParameter;
import com.feit.feep.dbms.entity.query.SortField;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/30 0030.
 */
public class TestDictionaryDao extends FeepJUnit {

    @Autowired
    private IFeepDictionaryDao dictionaryDao;

    @Test
    public void test() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setShowname("是否");
        dictionary.setDictionaryname("test");
        dictionary.setDescription("test");
        String id = dictionaryDao.addDictionary(dictionary);
        Global.getInstance().logInfo(id);
        dictionary.setId(id);
        dictionary.setDescription("表示是与非");
        dictionary.setDictionaryname("isorno");
        dictionaryDao.modifyDictionary(dictionary);
        int i = dictionaryDao.getTotalCount();
        Global.getInstance().logInfo(i);
        Dictionary dictionary1 = dictionaryDao.findDictionaryById(id);
        Assert.assertEquals(dictionary1.getDescription(), dictionary.getDescription());
        FeepQueryBean feepQueryBean = new FeepQueryBean();
        feepQueryBean.setPageSize(1);
        feepQueryBean.setPageSize(20);
        List<QueryParameter> list = new LinkedList<QueryParameter>();
        list.add(new QueryParameter("dictionaryname", "isorno", Condition.EQUALS));
        feepQueryBean.setQueryParameters(list);
        List<SortField> sortFields = new LinkedList<SortField>();
        sortFields.add(new SortField("dictionaryname"));
        feepQueryBean.setSortFields(sortFields);
        List<Dictionary> result = dictionaryDao.queryDictionary(feepQueryBean);
        Assert.assertEquals(dictionary1.getShowname(), result.get(0).getShowname());
        boolean ret = dictionaryDao.deleteDictionaryById(id);
        Assert.assertTrue(ret);
    }
}
