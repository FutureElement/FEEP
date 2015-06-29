package com.feit.feep.dbms.dao;

import com.feit.feep.dbms.entity.dictionary.DictionaryItem;
import com.feit.feep.exception.dbms.TableException;

import java.util.List;

/**
 * 数据字段项DAO接口
 * Created by ZhangGang on 2015/6/28 0028.
 */
public interface IFeepDictionaryItemDao {

    public String addItem(DictionaryItem item) throws TableException;

    public String[] addItems(List<DictionaryItem> itemList) throws TableException;

    public boolean deleteItemById(String id) throws TableException;

    public boolean deleteItemByIds(String[] ids) throws TableException;

    public boolean deleteItemByDictionaryId(String parentId) throws TableException;

    public boolean udpateItemInfo(DictionaryItem item) throws TableException;

    public DictionaryItem findDictionaryItemById(String id) throws TableException;

    public List<DictionaryItem> findDictionaryChildrenItemsById(String parentId) throws TableException;

    public List<DictionaryItem> findDictionaryItemsByDictionaryId(String parentId) throws TableException;

    public List<DictionaryItem> findAllItems() throws TableException;

}
