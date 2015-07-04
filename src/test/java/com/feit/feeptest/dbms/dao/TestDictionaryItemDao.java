package com.feit.feeptest.dbms.dao;

import com.feit.feep.config.junit.FeepJUnit;
import com.feit.feep.dbms.dao.IFeepDictionaryItemDao;
import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZhangGang on 2015/6/30 0030.
 */
public class TestDictionaryItemDao extends FeepJUnit {

    @Autowired
    private IFeepDictionaryItemDao dictionaryItemDao;

    @Test
    public void test() throws Exception {
        int count = 5;
        DictionaryItem item = new DictionaryItem("1230", "000", "test0", 0, "aaa0", "ttt", null);
        String id = dictionaryItemDao.addItem(item);
        List<DictionaryItem> itemList = new LinkedList<DictionaryItem>();
        for (int i = 1; i < count; i++) {
            itemList.add(new DictionaryItem(null, "00" + i, "test" + i, i, "aaa" + i, "ttt", null));
        }
        String[] ids = dictionaryItemDao.addItems(itemList);
        item.setId(id);
        item.setDescription("gggg");
        item.setSortnum(99);
        item.setDictionaryid("ttt2");
        boolean update = dictionaryItemDao.udpateItemInfo(item);
        Assert.assertTrue(update);
        List<DictionaryItem> itemList2 = dictionaryItemDao.findAllItems();
        Assert.assertEquals(itemList2.size(), count);
        DictionaryItem findItem = dictionaryItemDao.findDictionaryItemById(id);
        Assert.assertTrue(findItem.equals(item));
        List<DictionaryItem> itemList3 = dictionaryItemDao.findDictionaryItemsByDictionaryId("ttt");
        Assert.assertEquals(itemList3.size(), count - 1);
        String id2 = dictionaryItemDao.addItem(new DictionaryItem("123456", "000", "test0", 0, "aaa0", "www", "ttt"));
        List<DictionaryItem> itemList4 = dictionaryItemDao.findDictionaryChildrenItemsById(id2);
        Assert.assertEquals(itemList4.size(), count - 1);
        dictionaryItemDao.deleteItemByDictionaryId("ttt2");
        dictionaryItemDao.deleteItemById(id2);
        dictionaryItemDao.deleteItemByIds(ids);
    }
}
