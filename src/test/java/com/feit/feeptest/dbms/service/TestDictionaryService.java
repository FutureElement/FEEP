package com.feit.feeptest.dbms.service;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.core.Global;
import com.feit.feep.dbms.entity.EntityBeanSet;
import com.feit.feep.dbms.entity.dictionary.Dictionary;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.dbms.entity.query.FeepQueryBean;
import com.feit.feep.dbms.service.IDictionaryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/7/2 0002.
 */
public class TestDictionaryService extends FeepJUnit {

    @Autowired
    private IDictionaryService dictionaryService;

    @Test
    public void test() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setShowname("是否");
        dictionary.setDictionaryname("test");
        dictionary.setDescription("test");
        List<DictionaryItem> itemList = new LinkedList<DictionaryItem>();
        itemList.add(new DictionaryItem(null, "001", "是", 0, null, null, null));
        itemList.add(new DictionaryItem(null, "002", "否", 1, null, null, null));
        String id = dictionaryService.addDictionary(dictionary, itemList);
        dictionary.setId(id);
        EntityBeanSet itemSet = dictionaryService.findDictionaryItemsByDictionaryId(id);
        itemList.clear();
        for (int i = 0; i < itemSet.size(); i++) {
            itemList.add(new DictionaryItem(itemSet.get(i)));
        }
        itemList.get(0).setDescription("aaa");
        dictionary.setDescription("aaa");
        itemList.add(new DictionaryItem(null, "003", "未知", 0, null, null, null));
        boolean update = dictionaryService.updateDictionary(dictionary, itemList);
        Assert.assertTrue(update);
        Dictionary newDictionary = dictionaryService.findDictionaryById(id);
        Assert.assertEquals(dictionary.getDescription(), newDictionary.getDescription());
        Dictionary dictionary2 = dictionaryService.findDictionaryByName(newDictionary.getDictionaryname());
        Assert.assertEquals(dictionary2.getDescription(), newDictionary.getDescription());
        EntityBeanSet itemSet2 = dictionaryService.findDictionaryItemsByDictionaryId(id);
        Assert.assertEquals(itemSet2.size(), 3);
        EntityBeanSet itemSet3 = dictionaryService.queryDictionary(new FeepQueryBean());
        Global.getInstance().logInfo(itemSet3.size());
        boolean del = dictionaryService.deleteDictionaryById(id);
        Assert.assertTrue(del);
    }
}
